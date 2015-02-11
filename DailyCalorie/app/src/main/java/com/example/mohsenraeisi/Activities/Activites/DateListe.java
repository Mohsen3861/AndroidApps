package com.example.mohsenraeisi.Activities.Activites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

//import com.example.mohsenraeisi.dailycalorie.R;

import baseDeDonnees.DbDates;
import baseDeDonnees.DbStatistiques;
import baseDeDonnees.DbUser;

public class DateListe extends Activity {
 private DbStatistiques dbHelper;
    private DbDates DateHelper;
    private DbUser UserHelper;

    TextView idealCal ;
    TextView imc ;
 public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.animation3, R.anim.animation4);

	}
 
public void onResume(){
	super.onResume();
	dbHelper = new DbStatistiques(this);

	  dbHelper.open();
	  Cursor cursor = dbHelper.fetchAllCountries();
	  if(cursor.getCount()==0)
		  finish();
	  else
	  showList();
	  
	  dbHelper.close();

	
}
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_liste);

        idealCal = (TextView) findViewById(R.id.idealTextView);
        imc = (TextView) findViewById(R.id.imcTextView);




		dbHelper = new DbStatistiques(this);
		  dbHelper.open();
        DateHelper = new DbDates(this);
        DateHelper.open();

        UserHelper = new DbUser(this);
        UserHelper.open();

		  Cursor cursor = dbHelper.fetchAllCountries();
		  if(cursor.getCount()==0)

			  finish();

		  else

		  showList();
		 
		  dbHelper.close();

        showInfo();


	}


    public void showInfo() {
        Cursor c = UserHelper.getUser();
        c.moveToFirst();

        String imc = c.getString(c.getColumnIndexOrThrow(
                DbUser.IMC));

        double doubleIMC= Double.parseDouble(imc);


        String idealCalorie = c.getString(c.getColumnIndexOrThrow(
                DbUser.CALORIEIDEAL));

        if(doubleIMC<16.5)
        this.imc.setText("Votre IMC : "+imc+" -> "+"famine");

        if(doubleIMC>16.5 && doubleIMC<18.5)
            this.imc.setText("Votre IMC : "+imc+" -> "+"maigreur");

        if(doubleIMC>18.5 && doubleIMC<25.0)
            this.imc.setText("Votre IMC : "+imc+" -> "+"corpulence normale");

        if(doubleIMC>25.0 && doubleIMC<30)
            this.imc.setText("Votre IMC : "+imc+" -> "+"surpoids");
        if(doubleIMC>30.0 && doubleIMC<35.0)
            this.imc.setText("Votre IMC : "+imc+" -> "+"obésité modérée");

        if(doubleIMC>35.0 && doubleIMC<40.0)
            this.imc.setText("Votre IMC : "+imc+" -> "+"obésité sérvère");

        if(doubleIMC>40.0 )
            this.imc.setText("Votre IMC : "+imc+" -> "+"obésité morbide");




        this.idealCal.setText("Votre Calories Ideal : "+idealCalorie+ " Kcal/jour");


    }
	public  void showList() {
		List<HashMap<String, String>> listdate= getDates ( );

        Cursor c = DateHelper.getUser();
        c.moveToFirst();
        int i=0;
        Log.e("size",""+c.getCount());
        Log.e("size list",""+listdate.size());
        if(listdate.size()>1){
        while(i<listdate.size()){

            String sommeGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.SOMME));
            Log.e("somme ",sommeGet);
            listdate.get(i).put("somme",sommeGet);

            c.moveToNext();
            i++;
        }}else {

            String sommeGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.SOMME));
            Log.e("size list",""+listdate.size());

            listdate.get(0).put("somme",sommeGet);
        }

        c.moveToFirst();
       do{

            String sommeGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.SOMME));
            Log.e("somme ",sommeGet);


        } while(c.moveToNext());


		  final ListView items = (ListView) findViewById(R.id.datelist);

		  final SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listdate, R.layout.alimentitem,
	                new String[] { "date" , "somme"}, new int[] {R.id.nomsousmenu,R.id.catcal});

	       items.setAdapter(mSchedule);

	       items.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
					int itempos=position;
					//String itemV = (String) list.getItemAtPosition(position);
					HashMap<String, String> map = (HashMap<String, String>) items.getItemAtPosition(itempos);
				
					Intent intent = new Intent (DateListe.this,DateStatistiques.class);
					intent.putExtra(DateStatistiques.MESS, map.get("date"));
					
			
					startActivity(intent);
					overridePendingTransition(R.anim.animation1,R.anim.animation2);
					 // Toast.makeText(getApplicationContext(),
		                    //  "Position :"+itempos+"  ListItem : " +itemV , Toast.LENGTH_LONG)
		                    //  .show();
				
				}
			});
		
	}
	
	
	
	
	
	
	public List<HashMap<String, String>> getDates ( ){
		
		Cursor cursor = dbHelper.fetchAllCountries();






		ArrayList<HashMap<String, String>> mArrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map=new HashMap<String, String>();

        if(cursor.getCount()==1){
            cursor.moveToFirst();

            map = new HashMap<String, String>();
            map.put("date",cursor.getString(3 ));
            map.put("somme",""+0);

            mArrayList.add(map);


            return mArrayList;
        }
        else {


            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);

                map = new HashMap<String, String>();
                map.put("date", cursor.getString(3));
                map.put("somme", "" + i);
                mArrayList.add(map);


            }


            List<HashMap<String, String>> removeduplicated = new ArrayList<HashMap<String, String>>();

            removeduplicated.add(mArrayList.get(0));

            int check = 0;
            int counter = 0;
            for (int i = 0; i < mArrayList.size(); i++) {
                for (int k = 0; k < mArrayList.size(); k++) {
                    if (mArrayList.get(i).get("date").equals(mArrayList.get(k).get("date"))) {
                        counter++;

                    }
                }
                if (counter == 1) {
                    removeduplicated.add(mArrayList.get(i));

                } else {

                    for (int j = 0; j < removeduplicated.size(); j++) {
                        if (mArrayList.get(i).get("date").equals(removeduplicated.get(j).get("date"))) {
                            check = 0;
                        } else if (j == removeduplicated.size() - 1 && check == 0)
                            check = 1;
                    }

                    if (check == 1){



                        removeduplicated.add(mArrayList.get(i));

                    }

                }
                counter = 0;

            }


            return removeduplicated;

        }

	
	
	
	
}
	
}