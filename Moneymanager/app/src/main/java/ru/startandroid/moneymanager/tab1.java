package ru.startandroid.moneymanager;

//import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

/**
 * Created by Валерий on 17.08.2017.
 */

public class tab1 extends Fragment {


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.tab1_frag, container, false);
        return myview;

    }


}
