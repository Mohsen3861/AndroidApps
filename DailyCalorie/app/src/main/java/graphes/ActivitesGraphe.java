package graphes;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.mohsenraeisi.Activities.Activites.R;
import baseDeDonnees.DbDates;
import baseDeDonnees.DbStatistiques;
import baseDeDonnees.DbUser;
//import com.example.mohsenraeisi.dailycalorie.R;
import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class ActivitesGraphe extends Activity {
    private DbDates DbDate;
    private DbUser DbUser;

    private DbStatistiques DbStatistique;

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.animation3, R.anim.animation4);

    }


    String caloriePicker(String name) {

        int numberStart = 0;
        int numberFinish = 0;
        char[] result = new char[30];
        char[] charArray = name.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '-') {
                numberStart = i;

            }
        }

        for (int i = numberStart; i < charArray.length; i++)
            if (charArray[i] == ' ')
                numberFinish = i;


        int k = 0;
        if (numberStart != 0 && numberFinish != 0)
            for (int i = numberStart + 1; i < numberFinish; i++) {
                result[k] = charArray[i];
                k++;

            }
        name = String.copyValueOf(result).trim();
        return name;


    }

    public Intent getIntent(Context context) {

        DbUser = new DbUser(context);
        DbUser.open();
        DbStatistique = new DbStatistiques(context);
        DbStatistique.open();
        DbDate = new DbDates(context);
        DbDate.open();
        int max = 10000;

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
        XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
        XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
        TimeSeries series = new TimeSeries("Calories");
        TimeSeries series2 = new TimeSeries("Calorie ideal pour vous");

        int x = 0;
        int size = 0;
        double sommeTmp = 0;
        double calorieDouble = 0.0;



        String DateActuel = "";
        String calorieDate;
        String calorie;
        Cursor cs = DbStatistique.fetchAllCountries();
        Cursor cd = DbDate.getUser();
        Cursor cu = DbUser.getUser();

        cu.moveToFirst();
        String calorieIdeal = cu.getString(cu.getColumnIndexOrThrow(
                DbUser.CALORIEIDEAL));
        double idealDouble= Double.parseDouble(calorieIdeal);

        cd.moveToFirst();
        do {
            DateActuel = cd.getString(cd.getColumnIndexOrThrow(
                    DbDates.DATE));

            cs.moveToFirst();
            do {
                calorieDate = cs.getString(cs.getColumnIndexOrThrow(
                        DbStatistique.KEY_DATE));

                calorie = cs.getString(cs.getColumnIndexOrThrow(
                        DbStatistique.KEY_CALORIE));

                //   Log.e("cd",""+calorie+"   "+calorieDate+"   "+DateActuel);


                if (calorieDate.equals(DateActuel)) {
                    //  Log.e("if",calorie+"   "+caloriePicker(calorie));
                    if (!caloriePicker(calorie).equals("")) {
                        calorieDouble = Double.parseDouble(caloriePicker(calorie));
                        sommeTmp = sommeTmp + calorieDouble;
                        sommeTmp = (double) ((int) (sommeTmp * 100)) / 100;
                        Log.e("somme ", "" + sommeTmp);
                    }

                }

            } while (cs.moveToNext());


            series.add(x + 1, sommeTmp);
            size++;
            mRenderer.addTextLabel(x + 1, DateActuel);
            Log.e("!!!!!!!", "" + sommeTmp + "   " + DateActuel);

            x++;
            series2.add(x, idealDouble);

            sommeTmp = 0;
        }
        while (cd.moveToNext());


        mRenderer.setXLabelsAlign(Paint.Align.CENTER);
        mRenderer.setXLabels(0);


        dataset.addSeries(series);
        dataset.addSeries(series2);
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer2);


        mRenderer.setYLabels(25);
        mRenderer.setPanEnabled(true, true);
        mRenderer.setYAxisMin(-max / 2);
        mRenderer.setYAxisMax(max);
        mRenderer.setPanLimits(new double[]{-2, size + 2, 0, max});
        mRenderer.setXLabelsAngle(-45);
        mRenderer.setShowCustomTextGrid(true);

        mRenderer.setBackgroundColor(Color.argb(250,250,250,250));
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setAntialiasing(true);
        mRenderer.setMarginsColor(Color.WHITE);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setAxesColor(Color.BLACK);
        mRenderer.setXLabelsColor(Color.BLACK);
        mRenderer.setYLabelsColor(0, Color.BLACK);
        mRenderer.setPointSize(7);
        mRenderer.setAxisTitleTextSize(20);
        mRenderer.setLabelsTextSize(20);// la taille des labels
        mRenderer.setChartTitle("Activités");
        mRenderer.setYTitle("Calories brulé");
        mRenderer.setXTitle("DATE");
        mRenderer.setShowGrid(true);
        mRenderer.setGridColor(Color.GRAY);
        mRenderer.setXLabelsPadding(20);
        mRenderer.setYLabelsPadding(10);
        int[] margins = {50, 50, 200, 50};
        mRenderer.setMargins(margins);
        mRenderer.setChartTitleTextSize(30);
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setLegendTextSize(30);
        mRenderer.setDisplayValues(true);


        // Customization time for line 1!
        renderer.setColor(Color.RED);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesTextSize(20);
        renderer.setChartValuesSpacing(20);
        renderer.setLineWidth(5);
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);


        // Customization time for line 2!
        renderer2.setColor(Color.GREEN);
        renderer2.setLineWidth(5);
        renderer2.setPointStyle(PointStyle.DIAMOND);
        // renderer2.setDisplayBoundingPoints(false);


        Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line Graph Title");
        return intent;

    }
}

