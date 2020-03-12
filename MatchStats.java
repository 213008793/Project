package com.example.pc.myapplication;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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


public class MatchStats extends Fragment
{

    protected static Entry entry;
    protected static int index;
    protected static Highlight highlight;

    RadioButton button,radioButton;

    RadioGroup radioGroup;
    TextView one,two,three,four,five,score1,score2;

    int Count_1 =0;
    int Count_2 =0;
    int Count_3 =0;
    int Count_4 =0;
    int Count_5 =0;
    int Count_6 =0;
    int Count_7 =0;
    private PieChart mChart;

    private OnFragmentInteractionListener mListener;

    public MatchStats()
    {
        // Required empty public constructor
    }


    @Override
    public void onStart()
    {
        super.onStart();






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_match_stats, container, false);


        button = view.findViewById(R.id.FirstHalf);
        radioButton = view.findViewById(R.id.SecondHalf);

        one = view.findViewById(R.id.first);
        two = view.findViewById(R.id.second);
        three = view.findViewById(R.id.third);
        four = view.findViewById(R.id.fourth);
        five = view.findViewById(R.id.fiveth);
        score1 = view.findViewById(R.id.score1);
        score2= view.findViewById(R.id.score2);
        radioGroup  = view.findViewById(R.id.radio);
        mChart = view.findViewById(R.id.pie1);
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
            public void onValueSelected( Entry e,  Highlight h)
            {

                if(e == null)
                {
                    return;
                }
                else
                {
                    if (h.getX() ==0.0 && radioGroup.getCheckedRadioButtonId() == R.id.firstHalf)
                    {
                        Count_1++;
                        Count_6++;

                        score1.setText(Integer.toString(Count_6));

                        one.setText(Integer.toString(Count_1));
                    }

                    else if(h.getX() ==0.0 && radioGroup.getCheckedRadioButtonId() == R.id.secondHalf)
                    {

                        one.setText(Integer.toString(Count_1));
                        Count_1++;
                        Count_7++;
                        score2.setText(Integer.toString(Count_7));
                    }

                    if (h.getX() ==1.0 && radioGroup.getCheckedRadioButtonId() == R.id.firstHalf)
                    {
                        Count_2++;
                        Count_6++;
                        score1.setText(Integer.toString(Count_6));
                        two.setText(Integer.toString(Count_2));
                    }

                    else if(h.getX() ==1.0 && radioGroup.getCheckedRadioButtonId() == R.id.secondHalf)
                    {

                        two.setText(Integer.toString(Count_2));
                        Count_2++;
                        Count_7++;
                        score2.setText(Integer.toString(Count_7));
                    }

                    if (h.getX() ==2.0 && radioGroup.getCheckedRadioButtonId() == R.id.firstHalf)
                    {
                        Count_3++;
                        Count_6++;
                        score1.setText(Integer.toString(Count_6));
                        three.setText(Integer.toString(Count_3));
                    }

                    else if(h.getX() ==2.0 && radioGroup.getCheckedRadioButtonId() == R.id.secondHalf)
                    {

                        three.setText(Integer.toString(Count_3));
                        Count_3++;
                        Count_7++;
                        score2.setText(Integer.toString(Count_7));

                    }

                    if (h.getX() ==3.0 && radioGroup.getCheckedRadioButtonId() == R.id.firstHalf)
                    {
                        Count_4++;
                        Count_6++;
                        score1.setText(Integer.toString(Count_6));
                        four.setText(Integer.toString(Count_4));
                    }

                    else if(h.getX() ==3.0 && radioGroup.getCheckedRadioButtonId() == R.id.secondHalf)
                    {

                        four.setText(Integer.toString(Count_4));
                        Count_4++;
                        Count_7++;
                        score2.setText(Integer.toString(Count_7));
                    }

                    if (h.getX() ==4.0 && radioGroup.getCheckedRadioButtonId() == R.id.firstHalf)
                    {
                        Count_5++;
                        Count_6++;
                        score1.setText(Integer.toString(Count_6));
                        five.setText(Integer.toString(Count_5));
                    }

                    else if (h.getX() ==4.0 && radioGroup.getCheckedRadioButtonId() == R.id.secondHalf)
                    {

                        five.setText(Integer.toString(Count_5));
                        Count_5++;
                        Count_7++;
                        score2.setText(Integer.toString(Count_7));
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





    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
