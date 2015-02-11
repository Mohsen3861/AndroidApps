package com.example.mohsenraeisi.Activities.Activites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.example.mohsenraeisi.dailycalorie.R;

import baseDeDonnees.DbStatistiques;
import baseDeDonnees.DbUser;
import graphes.Graphes;
//NOTE :: on DateList for the first time of adding a aliment , il shows twice the date 


public class MainMenu extends Activity {
	private DbStatistiques dbHelper;
    private DbUser dbUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		dbHelper = new DbStatistiques(this);
        dbUser = new DbUser(this);
		Button aliments = (Button) findViewById(R.id.buttonAliment);
		Button activity = (Button) findViewById(R.id.buttonActivity);
		Button statistiques = (Button) findViewById(R.id.buttunStatistiques);
        Button graphes = (Button) findViewById(R.id.buttonGraphes);


/*
        TextView imc = (TextView) findViewById(R.id.imc);
        TextView ideal = (TextView) findViewById(R.id.ideal);

        dbUser.open();
        Cursor cursor = dbUser.getUser();
        cursor.moveToLast();


            String imctext = cursor.getString(cursor.getColumnIndexOrThrow(
                    DbUser.IMC));

            String idealtext = cursor.getString(cursor.getColumnIndexOrThrow(
                    DbUser.CALORIEIDEAL));

            imc.setText("votre IMC est de : " + imctext);
            ideal.setText("votre calorie ideal est de : "+idealtext);
*/
		aliments.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainMenu.this, CategorieListe.class);
				startActivity(intent);
				overridePendingTransition(R.anim.animation1,R.anim.animation2);
			}
		});
		
		
		
		activity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainMenu.this, ActivityListe.class);
				startActivity(intent);
				overridePendingTransition(R.anim.animation1,R.anim.animation2);
				
			}
		});
		
		
statistiques.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 dbHelper.open();
				
					Cursor cursor = dbHelper.fetchAllCountries();
					  //Generate ListView from SQLite Database
						if (cursor.getCount()!=0){
							Intent intent = new Intent(MainMenu.this, DateListe.class);
							startActivity(intent);
							overridePendingTransition(R.anim.animation1,R.anim.animation2);
						}
						else{
							Toast.makeText(getApplicationContext(), "la base de donnee est vide",Toast.LENGTH_SHORT).show();
						}
				  dbHelper.close();
		
			}
		});

        graphes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Graphes.class);
                startActivity(intent);
                overridePendingTransition(R.anim.animation1,R.anim.animation2);
            }
        });
		
	}

}
