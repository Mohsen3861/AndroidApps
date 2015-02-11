package com.example.mohsenraeisi.Activities.Activites;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

//import com.example.mohsenraeisi.dailycalorie.R;

import java.util.ArrayList;
import java.util.HashMap;

import baseDeDonnees.DbDates;
import baseDeDonnees.DbStatistiques;
import baseDeDonnees.DbUser;


public class DateStatistiques extends ActionBarActivity {
private DbDates DateHelper;
    private DbUser UserHelper;

public static final String MESS = "message10.txt";
public static  String DATE;
	 private DbStatistiques dbHelper;
	 private SimpleCursorAdapter dataAdapter;
	  ListView listView;
	 private int signe = 0;
      private double calorieideal;
	  ImageButton remove;
	  TextView sommetext;
      TextView comentaire;
	  View last ; 
	  String ItemIdSelected;
	  boolean clicked = false;
	  
	   View itemview;
	  
	 public void onBackPressed() {
		    super.onBackPressed();
		    overridePendingTransition(R.anim.animation3, R.anim.animation4);

		}
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_statistique);
	 DateHelper = new DbDates(this);
         DateHelper.open();

         UserHelper = new DbUser(this);
         UserHelper.open();

	  dbHelper = new DbStatistiques(this);
	  dbHelper.open();
	 
	  sommetext=(TextView) findViewById(R.id.sommelist);
      comentaire = (TextView) findViewById(R.id.comentaire);


	  Intent intent= getIntent();
		
	  DATE =intent.getStringExtra(MESS);

	  displayListView();
	  
	 // calculerSomme ();

         Cursor cu = UserHelper.getUser();

         cu.moveToFirst();

         String calorieIdeal = cu.getString(cu.getColumnIndexOrThrow(
                 DbUser.CALORIEIDEAL));

         calorieideal= Double.parseDouble(calorieIdeal);

         calculerSomme ();
	 
	 }


	 void calculerSomme () {
		 
		  double somme =0 ;
		   String name="";

		   
		   Cursor item=   (Cursor) dbHelper.fetchCountriesByDate(DATE);
		   item.moveToFirst();
		   if(item.getCount()==0){
finish();
overridePendingTransition(R.anim.animation3, R.anim.animation4);

		   }
		   else{
		   do {
		   name = item.getString(item.getColumnIndexOrThrow(
		    		dbHelper.KEY_CALORIE));
		   
		   name = caloriePicker(name);

		   if(signe==0)
			   somme = somme + Double.parseDouble(name);
		   if(signe==1)
			   somme = somme - Double.parseDouble(name);
		   
		   } while(item.moveToNext());
		   }
		   
		somme = (double)((int)(somme*100))/100;// garder just 2 chiffres apres le virgul
         if(somme>calorieideal+100){
             comentaire.setText("Vous avez un exces de calories ");
             comentaire.setTextColor(Color.RED);
         }
         else if(somme<calorieideal-100){
             comentaire.setText("Vous avez un manque de calories ");

             comentaire.setTextColor(Color.YELLOW);

         }
         else if((calorieideal-100<somme) && (calorieideal+100>somme)){
             comentaire.setText("Votre calories est idéal");
             comentaire.setTextColor(Color.GREEN);

         }

      saveSomme(somme);
		sommetext.setText(" TOTAL : "+somme);
	 
	 }



    int getCount() {

        Cursor c = DateHelper.getUser();
        int count=0;
       if(c.getCount()==0)
           return 0;
        c.moveToFirst();
        do {
            String dateGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.DATE));
            if(dateGet.equals(DATE)){
                count++;
            }

        }while(c.moveToNext());

        return count;

    }

    String getId() {

        Cursor c = DateHelper.getUser();
        String id=null;
        if(c.getCount()==0)
            return null;
        c.moveToFirst();
        do {
            String dateGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.DATE));
            String idGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.ROWID));
            if(dateGet.equals(DATE)){
                id=idGet;
            }

        }while(c.moveToNext());

        return id;

    }
    public void saveSomme(double Somme){
        String sommeString = Somme+"";

     //   if(getCount()==0)
       // DateHelper.createRecord(sommeString,DATE);

           String id= getId();
            DateHelper.updateRecordByDate(id, sommeString );


        if(Somme==0.0){
            DateHelper.deleteItemId(id);
        }






    }










	 
	 String caloriePicker(String name){
		 
		 int numberStart=0;
		   int numberFinish=0;
		   char[] result = new char[20] ;
		   char[] charArray = name.toCharArray();
		   
		   for (int i=0 ; i< charArray.length ;i++ ){
			   if(charArray[i]=='+' | charArray[i]=='-'){
				   numberStart=i;
				  if( charArray[i]=='+')
					  signe=0;
				  else
					  signe=1;
		   
			   }
		  
		   
			   
		   }
		   
		   for (int i=numberStart ; i< charArray.length ;i++ )
			   if(charArray[i]==' ')
				   numberFinish=i;
		   
		  
		   
		   int k=0;
		   for (int i=numberStart+1 ; i< numberFinish ;i++ ){
			   result[k]=charArray[i];
			   k++;
			   
		   }
		   name = String.copyValueOf(result).trim();
		   return name;
		 
		 
	 }



	
	 

	private void displayListView() {






         Cursor cursor = dbHelper.fetchCountriesByDate(DATE);



         // The desired columns to be bound
         String[] columns = new String[] {

                 DbStatistiques.KEY_NAME,
                 DbStatistiques.KEY_CALORIE,
                 DbStatistiques.KEY_DATE
         };

         // the XML defined views which the data will be bound to

         int[] to = new int[] {

                 R.id.itemname3,
                 R.id.itemcalorie,
                 R.id.itemdate
         };

         // create the adapter using the cursor pointing to the desired data
         //as well as the layout information
         dataAdapter = new SimpleCursorAdapter(
                 this, R.layout.statistiqueitem,
                 cursor,
                 columns,
                 to,
                 0);

         listView = (ListView) findViewById(R.id.listcat);
         // Assign adapter to ListView
         listView.setAdapter(dataAdapter);

        ((AbsListView) listView).setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        registerForContextMenu(listView);


     }
		

		  
		  
		

		  
	
	   public void onCreateContextMenu(ContextMenu menu, View v,
	    		ContextMenuInfo menuInfo) {
	    	if (v.getId()==R.id.listcat) {
	    	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    	//	menu.setHeaderTitle(info.id.);
	    	    String[] menuItems = {"Supprimer"};
	    		for (int i = 0; i<menuItems.length; i++) {
	    			menu.add(Menu.NONE, i, i, menuItems[i]);
				}
	    	}
	    }
	  
	   
	   // source : http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/
	 
	   
	   public boolean onContextItemSelected(MenuItem item) {
		    final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		 
		    int menuItemIndex = item.getItemId();  
			String[] menuItems = {"Supprimer"};
			String menuItemName = menuItems[menuItemIndex];
			
			if(menuItemName==menuItems[0]){
                Log.e("test"," in delete  2 "+info.position );

                dbHelper.deleteItemId(""+listView.getItemIdAtPosition(info.position));
						displayListView();
						calculerSomme ();

			}
			
			
	    	return true;
	    }
	 

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistique, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {

            AlertDialog.Builder builder = new AlertDialog.Builder(DateStatistiques.this);
         builder.setMessage("vous êtes sûr ? ").setTitle("Effacer les données");

            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dbHelper.deleteAll();
                    DateHelper.deleteAll();
                    finish();
                    //displayListView();

                }
            });
            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                     // User cancelled the dialog
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
