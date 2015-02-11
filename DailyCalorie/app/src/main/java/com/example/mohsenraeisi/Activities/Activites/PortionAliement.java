package com.example.mohsenraeisi.Activities.Activites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.mohsenraeisi.dailycalorie.R;

import baseDeDonnees.DbDates;
import baseDeDonnees.DbStatistiques;

public class PortionAliement extends Activity {

	 private DbStatistiques dbHelper;
    private DbDates DateHelper;
	public final static String MESSAGE_EXTRA="Message.text";
	public final static String MESSAGE_EXTRA2="Message2.text";
    public final static String MESSAGE_EXTRA3="Message3.text";

	public static int prog;
	double calorie ;
	public static ArrayList<HashMap<String, String>> StatistiquesListe = new ArrayList<HashMap<String, String>>();
	
	
	
	
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.animation3, R.anim.animation4);

	}
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aliments_quantite);
		dbHelper = new DbStatistiques(this);
		  dbHelper.open();

        DateHelper = new DbDates(this);
        DateHelper.open();
		final TextView txt=(TextView) findViewById(R.id.nombutton);
		final TextView txt2=(TextView) findViewById(R.id.PortionQuantité);
        final String nomerecord ,calrecord;

		
  

		Intent intent= getIntent();
		
		 final String message1 = intent.getStringExtra(MESSAGE_EXTRA);
		 final String message2 = intent.getStringExtra(MESSAGE_EXTRA2);
         final String message3 = intent.getStringExtra(MESSAGE_EXTRA3);

	   
	final int quantite = getQuantitéFromString(message3);
    final String unit = getQuantiteUnite(message3);

txt2.setText("0 "+unit.toUpperCase());

		txt.setText(message1);
		nomerecord=message1;
		calrecord=message2;
		calorie = Double.parseDouble(message2);
		
		
		

		
		final SeekBar seek=(SeekBar) findViewById(R.id.seekBar1);
		
		final Button bttn = (Button) findViewById(R.id.bttn1);
            bttn.setEnabled(false);

	     seek.setMax(quantite*35);



        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(quantite>1 )
				progress= progress-progress%50;

			prog = progress;

            if(progress>0)
                bttn.setEnabled(true);
                else
                bttn.setEnabled(false);
                    txt2.setText(""+progress+" "+unit.toUpperCase()+" ");


				
			}
		});
			
		bttn.setOnClickListener( new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				
				double calorietotal =  (double) (  (double) prog / quantite ) * calorie   ;

                Log.e("total ", ""+calorietotal);
				
				
					Calendar c = Calendar.getInstance();

				SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
				String formattedDate = df.format(c.getTime());
				dbHelper.createRecord(message1,
						 "presque "+ txt2.getText().toString() + "=+" +calorietotal+ " Kcal" , formattedDate   );

                Cursor cursor = dbHelper.fetchAllCountries();
                Cursor cDates = DateHelper.getUser();
                Log.e(" count c",""+cursor.getCount());
                if(!Existe(formattedDate)){
                    Log.e("exite",formattedDate);
                    DateHelper.createRecord(""+calorietotal,formattedDate);

                }

                Toast toast = Toast.makeText(getApplicationContext(),"Bien enregistré "+calorietotal+ " Kcal :)" , Toast.LENGTH_SHORT);
                toast.show();

                onBackPressed();



				
				
				// source : https://gist.github.com/kwent/5875749
				
			}
				
				
		});




	
		
		
	}


    public boolean Existe(String date){
        Cursor cDates = DateHelper.getUser();
        cDates.moveToFirst();
        if(cDates.getCount()==0)
            return false;
        do {
            String dateGet = cDates.getString(cDates.getColumnIndexOrThrow(
                    DbDates.DATE));
            if(dateGet.equals(date)) {
                return true;

            }

        }while(cDates.moveToNext());
        return false;
    }


	public int getQuantitéFromString(String s){

       char sChar[]= s.toCharArray();
        char  CharResulat [] =  new char[3];

        String Result = null;

        int k = 0;
        for(int i = 0  ; i< sChar.length-1 ; i++ ){

            if( sChar[i]==' '){

                break;
            }

            CharResulat[k]= sChar[i];

            k++;

        }

       Result =  String.copyValueOf(CharResulat).trim(); // trime(): supprimer les cases non-utilisé
        Log.e("d",Result);

        return  Integer.parseInt(Result);

    }


    public String getQuantiteUnite(String s){

        char sChar[]= s.toCharArray();
        char  CharResulat [] =  new char[20];
        String Result = null;

        int k = 0;
        int Start=0;
        for(int i = 0  ; i< sChar.length ; i++ ) {

            if (sChar[i] == ' ') {
                Start=i;
                break;
            }

        }

        for(int i = Start  ; i< sChar.length ; i++ ) {

            CharResulat[k]= sChar[i];

            k++;

        }


        Result = String.copyValueOf(CharResulat).trim();
     return  Result;

    }

	
	
	

}
