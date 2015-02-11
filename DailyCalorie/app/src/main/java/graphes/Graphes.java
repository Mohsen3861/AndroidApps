package graphes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mohsenraeisi.Activities.Activites.R;

//import com.example.mohsenraeisi.dailycalorie.R;


public class Graphes extends Activity {


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation3, R.anim.animation4);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphes);

        Button aliments = (Button) findViewById(R.id.GrapheAliments);
        Button activites = (Button) findViewById(R.id.GrapheActivites);
        Button total = (Button) findViewById(R.id.GrapheTotal);



    }

    public void alimentClick(View view)
    {
        AlimentsGraphe line = new AlimentsGraphe();
        Intent lineIntent = line.getIntent(this);
        startActivity(lineIntent);
        overridePendingTransition(R.anim.animation1,R.anim.animation2);

    }

    public void activitesClick(View view)
    {
        ActivitesGraphe line = new ActivitesGraphe();
        Intent lineIntent = line.getIntent(this);
        startActivity(lineIntent);
        overridePendingTransition(R.anim.animation1,R.anim.animation2);

    }

    public void totalClick(View view)
    {
        TotalGraphe line = new TotalGraphe();
        Intent lineIntent = line.getIntent(this);
        startActivity(lineIntent);
        overridePendingTransition(R.anim.animation1,R.anim.animation2);

    }


}
