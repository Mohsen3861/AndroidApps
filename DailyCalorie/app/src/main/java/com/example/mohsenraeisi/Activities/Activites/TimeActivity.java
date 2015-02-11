package com.example.mohsenraeisi.Activities.Activites;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class TimeActivity extends Activity {
	private DbStatistiques dbHelper;
    private DbDates DateHelper;
	public static final String MESSAGE_NOM = "message6.txt";
	public static final String MESSAGE_CALORIE = "message7.txt";
	public static double calorietotal;
	
	
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.animation3, R.anim.animation4);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time);
		final TextView txt=(TextView) findViewById(R.id.activitynom);
		final TextView txt2=(TextView) findViewById(R.id.activitytime);	
		final TextView txt3=(TextView) findViewById(R.id.calculcalorietime);	
		final SeekBar seek=(SeekBar) findViewById(R.id.seekBar2);
		final double calorieActiity;
		final Button ajouter = ( Button ) findViewById(R.id.bttnactivityadd);
        ajouter.setEnabled(false);
		dbHelper = new DbStatistiques(this);
		  dbHelper.open();

        DateHelper = new DbDates(this);
        DateHelper.open();
		Intent intent = getIntent();

		txt.setText(intent.getStringExtra(MESSAGE_NOM));
		calorieActiity = Double.parseDouble(intent.getStringExtra(MESSAGE_CALORIE)) ;
		
		
	    seek.setMax(480);
		
			 seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				
				int heur=0;
				int min=10;
				boolean changedtxt=false;
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
					
					changedtxt=false;
					

					
					heur = progress / 60 ;
					min = (progress%60);
					min = min - (min%5);

                    if(progress>0)
                        ajouter.setEnabled(true);
                    else
                        ajouter.setEnabled(false);

                    calorietotal=   ( (float) progress/60 ) * calorieActiity;
					calorietotal=(double)((int)(calorietotal*100))/100;// garder 2 chiffres apres la virgule
					

						txt3.setText(""+calorietotal+ " Kcal");
					
					
					
					if (min == 0 || min == 5){
						txt2.setText("0"+heur+" : "+"0"+min);
						changedtxt=true;
					}
					else{
						txt2.setText("0"+heur+" : "+min);
						changedtxt=true;
					}
					
					
				}
			});
		
			 
			 ajouter.setOnClickListener( new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//int prog = Integer.parseInt(txt2.getText().toString());

						
						// enregistrer dans la BDD
						
						Calendar c = Calendar.getInstance();
						//System.out.println("Current time => " + c.getTime());

						SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
						String formattedDate = df.format(c.getTime());
						Toast toast = Toast.makeText(getApplicationContext(),"Bien enregistré "+calorietotal+ " Kcal :)" , Toast.LENGTH_SHORT);
						 toast.show();
						 
						 dbHelper.createRecord(txt.getText().toString(),
								 "presque "+ txt2.getText().toString() + "=-" +calorietotal+ " Kcal" ,
								 formattedDate   );


                        if(!Existe(formattedDate)){
                            Log.e("exite", formattedDate);
                            DateHelper.createRecord(""+calorietotal,formattedDate);

                        }


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
}
