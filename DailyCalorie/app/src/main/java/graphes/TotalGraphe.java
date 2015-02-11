package graphes;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;

import baseDeDonnees.DbDates;
import baseDeDonnees.DbUser;

public class TotalGraphe extends Activity{
    private DbDates DateHelper;
    private DbUser DbUser;
    public Intent getIntent (Context context){

        DateHelper = new DbDates(context);
        DateHelper.open();

        DbUser = new DbUser(context);
        DbUser.open();

        int max = 10000;

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
        XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
        XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
        TimeSeries series = new TimeSeries("Calories");
        TimeSeries series2 = new TimeSeries("Calorie ideal pour vous");

        int x =0;
        int size=0;

        Cursor cu = DbUser.getUser();

        cu.moveToFirst();
        String calorieIdeal = cu.getString(cu.getColumnIndexOrThrow(
                DbUser.CALORIEIDEAL));
        double idealDouble= Double.parseDouble(calorieIdeal);

        Cursor c = DateHelper.getUser();
        c.moveToFirst();
        do {
            String dateGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.DATE));
            String sommeGet = c.getString(c.getColumnIndexOrThrow(
                    DbDates.SOMME));
            // Log.e("infos ",""+sommeGet+" "+dateGet);
            size = c.getCount();
            series.add(x+1,Double.parseDouble(sommeGet) );
            mRenderer.addTextLabel(x+1, dateGet);


            x++;
            series2.add(x, idealDouble);
        }while(c.moveToNext());





        mRenderer.setXLabelsAlign(Paint.Align.CENTER);
        mRenderer.setXLabels(0);






        dataset.addSeries(series);
        dataset.addSeries(series2);
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer2);


        mRenderer.setYLabels(25);
        mRenderer.setPanEnabled(true, true);
        // mRenderer.setPanLimits(new double[] { minX, maxX, minY, maxY});
        mRenderer.setYAxisMin(-max/2);
        mRenderer.setYAxisMax(max);
        mRenderer.setPanLimits(new double[]{-2, size+2, 0, max});
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
        mRenderer.setChartTitle("Total de calories");
        mRenderer.setYTitle("CALORIES");
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
