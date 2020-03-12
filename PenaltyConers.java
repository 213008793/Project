package com.example.pc.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PenaltyConers extends Fragment
{

    protected static Entry entry;
    protected static int index;
    protected static Highlight highlight;


    TextView one,two,three,four,five,score3,score4;

    int Count_1 =0;
    int Count_2 =0;
    int Count_3 =0;
    int Count_4 =0;
    int Count_5 =0;
    int Count_6 =0;
    int Count_7 =0;
    private PieChart mChart;
    RadioGroup radioGroup;

    public PenaltyConers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penalty_coners, container, false);

        one = view.findViewById(R.id.First);
        two = view.findViewById(R.id.Second);
        three = view.findViewById(R.id.Third);
        four = view.findViewById(R.id.Fourth);
        five = view.findViewById(R.id.Fiveth);

        score3 = view.findViewById(R.id.score3);
        score4 = view.findViewById(R.id.score4);

        radioGroup = view.findViewById(R.id.Penalty);

        mChart = view.findViewById(R.id.pie2);
        mChart.setBackgroundColor(Color.WHITE);

        mChart.setUsePercentValues(false);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setDrawHoleEnabled(false);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleRadius(61f);

        mChart.setMaxAngle(180);
        mChart.setRotationAngle(180);
        mChart.setRotationEnabled(false);

        final ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(25f, " "));
        yValues.add(new PieEntry(25f, ""));
        yValues.add(new PieEntry(25f, ""));
        yValues.add(new PieEntry(25f, ""));
        yValues.add(new PieEntry(25f, ""));

        final PieDataSet dataSet = new PieDataSet(yValues, "Score");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        final PieData data = new PieData(dataSet);
        mChart.setData(data);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                if(e == null)
                {
                    return;
                }
                else
                {
                    if (h.getX() ==0.0 && radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_Pen)
                    {
                        Count_1++;
                        Count_6++;

                        score3.setText(Integer.toString(Count_6));

                        one.setText(Integer.toString(Count_1));
                    }

                    else if(h.getX() ==0.0 && radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_Pen)
                    {

                        one.setText(Integer.toString(Count_1));
                        Count_1++;
                        Count_7++;
                        score4.setText(Integer.toString(Count_7));
                    }

                    if (h.getX() ==1.0 && radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_Pen)
                    {
                        Count_2++;
                        Count_6++;
                        score3.setText(Integer.toString(Count_6));
                        two.setText(Integer.toString(Count_2));
                    }

                    else if(h.getX() ==1.0 && radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_Pen)
                    {

                        two.setText(Integer.toString(Count_2));
                        Count_2++;
                        Count_7++;
                        score4.setText(Integer.toString(Count_7));
                    }

                    if (h.getX() ==2.0 && radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_Pen)
                    {
                        Count_3++;
                        Count_6++;
                        score3.setText(Integer.toString(Count_6));
                        three.setText(Integer.toString(Count_3));
                    }

                    else if(h.getX() ==2.0 && radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_Pen)
                    {

                        three.setText(Integer.toString(Count_3));
                        Count_3++;
                        Count_7++;
                        score4.setText(Integer.toString(Count_7));

                    }

                    if (h.getX() ==3.0 && radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_Pen)
                    {
                        Count_4++;
                        Count_6++;
                        score3.setText(Integer.toString(Count_6));
                        four.setText(Integer.toString(Count_4));
                    }

                    else if(h.getX() ==3.0 && radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_Pen)
                    {

                        four.setText(Integer.toString(Count_4));
                        Count_4++;
                        Count_7++;
                        score4.setText(Integer.toString(Count_7));
                    }

                    if (h.getX() ==4.0 && radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_Pen)
                    {
                        Count_5++;
                        Count_6++;
                        score3.setText(Integer.toString(Count_6));
                        five.setText(Integer.toString(Count_5));
                    }

                    else if (h.getX() ==4.0 && radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_Pen)
                    {

                        five.setText(Integer.toString(Count_5));
                        Count_5++;
                        Count_7++;
                        score4.setText(Integer.toString(Count_7));
                    }

                }
            }

            @Override
            public void onNothingSelected()
            {

            }
        });


        return view;
    }

}
