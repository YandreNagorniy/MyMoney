package ru.startandroid.moneymanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {


    PieChart mChart;
    // we're going to display pie chart for school attendance

    //значение заполнения диаграммы(с цифрами)
    private int[] yValues = {21, 2, 2};
    //элементы
    private String[] item = {"Еда", "Одежда", "Бензин"};

    // цвета для разных разделов в диаграмме
    public static final int[] MY_COLORS = {
            Color.rgb(153, 19, 0), Color.rgb(215, 60, 55), Color.rgb(84, 124, 101), Color.rgb(64, 64, 64),
            Color.rgb(38, 40, 53)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mChart = (PieChart) findViewById(R.id.chart1);

        //   mChart.setUsePercentValues(true);
        mChart.setDescription("");

        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

// Обработчик нажатия элемента диаграммы
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // Отображать сообщение при выбранном значении
                if (e == null)
                    return;

                Toast.makeText(TestActivity.this,
                        item[e.getXIndex()] + " is " + e.getVal() + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Worck_space.class);
                startActivity(intent);

            }

            @Override
            public void onNothingSelected() {

            }
        });

        // setting sample Data for Pie Chart
        setDataForPieChart();
    }

    public void setDataForPieChart() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yValues.length; i++)
            yVals1.add(new Entry(yValues[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < item.length; i++)
            xVals.add(item[i]);

        // create pieDataSet
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // adding colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // Added My Own colors
        for (int c : MY_COLORS)
            colors.add(c);


        dataSet.setColors(colors);

        //  create pie data object and set xValues and yValues and set it to the pieChart
        PieData data = new PieData(xVals, dataSet);
        //   data.setValueFormatter(new DefaultValueFormatter());
        //   data.setValueFormatter(new PercentFormatter());

        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // refresh/update pie chart
        mChart.invalidate();

        // animate piechart
        mChart.animateXY(1400, 1400);


        // Legends to show on bottom of the graph
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }


    public static class MyValueFormatter implements ValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0"); // use one decimal if needed
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here
            return mFormat.format(value) + ""; // e.g. append a dollar-sign
        }
    }


}