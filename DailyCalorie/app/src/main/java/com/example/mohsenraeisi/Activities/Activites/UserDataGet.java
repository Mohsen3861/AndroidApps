package com.example.mohsenraeisi.Activities.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.mohsenraeisi.dailycalorie.R;

import baseDeDonnees.DbUser;


public class UserDataGet extends Activity {
    private DbUser dbHelper;

    EditText ageText;
    EditText poidsText;
    EditText tailleText;

    CheckBox aucune;
    CheckBox onetotree;
    CheckBox fourtofive;
    CheckBox sixtoseven;
    CheckBox twiceperday;

    CheckBox homme ;
    CheckBox femme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_get);
        Log.e("running","in get");

        ageText = (EditText) findViewById(R.id.editTextAge);
        poidsText = (EditText) findViewById(R.id.editTextPoids);
        tailleText = (EditText) findViewById(R.id.editTextTaille);

        aucune = (CheckBox) findViewById(R.id.rien);
        onetotree = (CheckBox) findViewById(R.id.troisparsemain);
        fourtofive = (CheckBox) findViewById(R.id.cinqparsemain);
        sixtoseven = (CheckBox) findViewById(R.id.septparsemain);
        twiceperday = (CheckBox) findViewById(R.id.deuxparjour);

        homme = (CheckBox) findViewById(R.id.homme);
        femme = (CheckBox) findViewById(R.id.femme);

        Button button =(Button) findViewById(R.id.validerButton);

        final SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);

        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
            finish();
        } else {



            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences.Editor ed = pref.edit();
                    ed.putBoolean("activity_executed", true);
                    ed.commit();
                    EnregistrerLesDonnées();
                    Toast.makeText(getApplicationContext()," Merci ! vos information sont bien enregistré ",Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(UserDataGet.this,MainMenu.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.animwelcome1,R.anim.animwelcome2);

                    finish();
                }
            });
       }



    }




public void EnregistrerLesDonnées() {

    int sexe =0 ;
    String ageString = ageText.getText().toString();
    String poidsString = poidsText.getText().toString();
    String tailleString = tailleText.getText().toString();
    String activiteparjour="";

    String imc ;
    String calorieIdeal;

    if(homme.isChecked())
        sexe = 1;
    if(femme.isChecked())
        sexe = 0;


    if(aucune.isChecked()){
        activiteparjour = "0";
    }
    if(onetotree.isChecked()){
        activiteparjour = "3";
    }
    if(fourtofive.isChecked()){
        activiteparjour = "5";
    }
    if(sixtoseven.isChecked()){
        activiteparjour = "7";
    }
    if(twiceperday.isChecked()){
        activiteparjour = "2";
    }

    imc = calculImc(tailleString,poidsString);

    calorieIdeal = calculCalorieIdeal(poidsString,tailleString,ageString,activiteparjour,sexe);

    saveUserSql(ageString,poidsString,tailleString,activiteparjour,imc,""+sexe,calorieIdeal);



}




String calculImc ( String t , String p) {

    double taille = Double.parseDouble(t)/100;
    double poids = Double.parseDouble(p);

    double resultat;

    resultat = poids / (taille * taille) ;
    resultat = (double)((int)(resultat*100))/100;
    return ""+resultat;

}




    String calculCalorieIdeal (String poids , String taille , String age , String activite,int sexe){


      double p = Double.parseDouble(poids);
      double t = Double.parseDouble(taille);
      double a = Double.parseDouble(age);
      int activity = Integer.parseInt(activite);


        double BMR ;

        if(sexe == 1 )
            BMR = (13.75 * p) + (5* t) - (6.75 * a) + 66 ;
        else
            BMR = (9.56 * p) + (1.85* t) - (4.68 * a) + 655 ;

        if(activity == 0 ) BMR*= 1.2;
        if(activity == 3 ) BMR*= 1.375;
        if(activity == 5 ) BMR*= 1.55;
        if(activity == 7 ) BMR*= 1.725;
        if(activity == 2 ) BMR*= 1.9;


        BMR = (double)((int)(BMR*100))/100;
        return ""+BMR;

    }





        /*
 *
 *
 *http://www.topendsports.com/testing/tests/bmr-equation.htm#
 *
 *
 *BMR formula: The Harris-Benedict Equation has for a long time been the standard formula and is widely used for estimating BMR. Use the calculations below to calculate BMR, where: Wt = weight in kg, Ht = height in cm, A = age in years.
men: kcal/day = (13.75 x Wt) + (5 x Ht) - (6.76 x Age) + 66
women: kcal/day = (9.56 x Wt) + (1.85 x Ht) - (4.68 x Age) + 655

Harris-Benedict Principle: Use the following table to calculate your recommended daily calorie intake to maintain current weight.
Exercise Level	Details	Calorie Calculation (Daily Needs)
Little to no exercise	 	BMR x 1.2
Light exercise	1-3 days per week	BMR x 1.375
Moderate exercise	3-5 days per week	BMR x 1.55
Heavy exercise	6-7 days per week	BMR x 1.725
Very heavy exercise	twice per day, extra heavy workouts	BMR x 1.9

*/





public void saveUserSql (String age,String poids,String taille,String activity , String imc , String sexe,String calorieideal) {

    dbHelper = new DbUser(this);
    dbHelper.open();
    dbHelper.deleteAll();
    dbHelper.createRecord(age,poids,taille,activity,imc,null,sexe,calorieideal);

}
























}
