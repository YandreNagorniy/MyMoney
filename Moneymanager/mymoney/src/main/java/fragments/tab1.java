package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import ru.startandroid.mymoney.R;


public class tab1 extends Fragment {


    PieChart mChart;
    // we're going to display pie chart for school attendance

    //значение заполнения диаграммы(с цифрами)
    private int[] yValues = {21, 2, 2,2,4,6,7};
    //элементы
    private String[] item = {"Еда", "Одежда", "Бензин","Одежда","Одежда","Одежда","Одежда"};

    // цвета для разных разделов в диаграмме
    public static final int[] MY_COLORS = {
            Color.rgb(153, 19, 0), Color.rgb(215, 60, 55), Color.rgb(84, 124, 101), Color.rgb(64, 64, 64),
            Color.rgb(38, 40, 53)
    };


    private  static  final String TAG = "tab1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View v =inflater.inflate(R.layout.tab_fragment1, container, false);

        mChart = (PieChart) v.findViewById(R.id.chart1);

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
//
//                Toast.makeText(MainActivity.this,
//                        item[e.getXIndex()] + " is " + e.getVal() + "", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), Worck_space.class);
//                startActivity(intent);

            }

            @Override
            public void onNothingSelected() {

            }
        });

        // setting sample Data for Pie Chart
        setDataForPieChart();
        return v;
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
        dataSet.setSelectionShift(25);

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
        //скорость анимации
        mChart.animateXY(1400, 1400);


        // Legends to show on bottom of the graph
        //расположение текста относительно диаграммы
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
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