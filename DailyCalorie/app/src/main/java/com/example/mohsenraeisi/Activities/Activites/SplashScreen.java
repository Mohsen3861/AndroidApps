package com.example.mohsenraeisi.Activities.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohsenraeisi.Activities.Activites.R;

public class SplashScreen extends Activity {
public static final int sec=3;
    public static final int milisecond = sec *1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        starting();
    }

    public void starting(){

        new CountDownTimer(milisecond,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(SplashScreen.this,UserDataGet.class);
                startActivity(intent);
                finish();

            }
        }.start();
    }


}
