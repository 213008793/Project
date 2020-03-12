package com.example.pc.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class TurnOver_goals extends Fragment {


    TextView turnOverHomeAdd,turnOverAwayAdd,TvturnOverHomeMinus,turnOverAwayMinus,GoalsAddHome,
    GoalsAwayAdd,GoalsHomeMinus,GoalsAwayMinus;

    RadioGroup radioGroup;

    ImageButton  turnoverAwayminus,HomeMinus,GoalsvsAdd,negAway,HomePlus,
            turnoverhomeminus,turnoverawayadd,goalsTeamAdd;



    int counter_turnOverHomeAdd =0;
    int counter_turnOverAwayAdd =0;
    int counter_TvturnOverHomeMinus =0;
    int counter_turnOverAwayMinus =0;
    int counter_GoalsAddHome =0;
    int counter_GoalsAwayAdd =0;
    int counter_GoalsHomeMinus =0;
    int counter_GoalsAwayMinus;

    private OnFragmentInteractionListener mListener;

    public TurnOver_goals() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turn_over_goals, container, false);

        turnOverHomeAdd = view.findViewById(R.id.turnOverVsFirstCount);//1st half
        turnOverAwayAdd = view.findViewById(R.id.turnOverTeamSecondCount);//scnd Half
        TvturnOverHomeMinus = view.findViewById(R.id.FirstCount);//1
        turnOverAwayMinus = view.findViewById(R.id.turnOverVsSecondCount);//2

        GoalsAddHome = view.findViewById(R.id.goalsTeamFirstCounter);//1
        GoalsHomeMinus = view.findViewById(R.id.goalsTeamSecondCounter);//2
        GoalsAwayAdd = view.findViewById(R.id.goalsVsFirstCounter);//1
        GoalsAwayMinus = view.findViewById(R.id.VsSecondGoals);//2
        radioGroup = view.findViewById(R.id.TurnOvers);



        HomeMinus = view.findViewById(R.id.HomeMinus);

        HomeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T&& counter_GoalsAddHome > 0)
                {
                    counter_GoalsAddHome--;



                    turnOverHomeAdd.setText(Integer.toString(counter_GoalsAddHome));
                }

                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T && counter_GoalsHomeMinus >0)
                {
                    counter_GoalsHomeMinus--;

                    turnOverAwayAdd.setText(Integer.toString(counter_GoalsHomeMinus));



                }
            }
        });
        goalsTeamAdd = view.findViewById(R.id.goalsTeamAdd);

        goalsTeamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {



                if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T)
                {
                    counter_GoalsAddHome++;

                    turnOverHomeAdd.setText(Integer.toString(counter_GoalsAddHome));
                }

                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T)
                {
                    counter_GoalsHomeMinus++;

                    turnOverAwayAdd.setText(Integer.toString(counter_GoalsHomeMinus));

                }
            }
        });

        GoalsvsAdd = view.findViewById(R.id.GoalsvsAdd);

        GoalsvsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T)
                {
                    counter_GoalsAwayAdd++;

                    TvturnOverHomeMinus.setText(Integer.toString(counter_GoalsAddHome));
                }

                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T )
                {
                    counter_GoalsAwayMinus++;
                    turnOverAwayMinus.setText(Integer.toString( counter_GoalsAwayMinus));


                }

            }
        });

        negAway = view.findViewById(R.id.negAway);
        negAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {



                if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T&&  counter_GoalsAwayMinus > 0)
                {
                    counter_GoalsAwayMinus--;

                    TvturnOverHomeMinus.setText(Integer.toString( counter_GoalsAwayMinus));
                }

                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T &&  counter_GoalsAwayMinus >0)
                {
                    counter_GoalsAwayMinus--;

                    turnOverAwayMinus.setText(Integer.toString(counter_GoalsHomeMinus));


                }

            }
        });

        turnoverhomeminus = view.findViewById(R.id.turnoverhomeminus);

        turnoverhomeminus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T&& counter_turnOverHomeAdd >0 )
                {
                    counter_turnOverHomeAdd--;
                    GoalsAddHome.setText(Integer.toString(counter_turnOverHomeAdd));
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T&&  counter_turnOverHomeAdd >0)
                {
                    counter_TvturnOverHomeMinus--;
                    GoalsHomeMinus.setText(Integer.toString(counter_TvturnOverHomeMinus));
                }

            }
        });


        HomePlus = view.findViewById(R.id.HomePlus);

        HomePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T)
                {

                    counter_turnOverHomeAdd++;

                    GoalsAddHome.setText(Integer.toString( counter_turnOverHomeAdd));
                }

                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T)
                {
                    counter_GoalsHomeMinus++;

                    GoalsHomeMinus.setText(Integer.toString(counter_GoalsHomeMinus));



                }

            }
        });




        turnoverawayadd = view.findViewById(R.id.turnoverawayadd);
        turnoverawayadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T)
                {
                    counter_turnOverAwayAdd++;
                    GoalsAwayAdd.setText(Integer.toString( counter_turnOverAwayAdd));
                }

                else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T )
                {
                    counter_turnOverAwayMinus++;
                    GoalsAwayMinus.setText(Integer.toString(counter_turnOverAwayMinus));
                }
            }
        });

        turnoverAwayminus = view.findViewById(R.id.turnOverAwayMinus);

        turnoverAwayminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if ( view.getId() == R.id.turnOverAwayMinus)
                {


                    if (radioGroup.getCheckedRadioButtonId() == R.id.FirstHalf_T && counter_turnOverAwayAdd>0 )
                    {
                        counter_turnOverAwayAdd--;
                        GoalsAwayAdd.setText(Integer.toString(counter_turnOverAwayAdd));
                    }

                    else if(radioGroup.getCheckedRadioButtonId() == R.id.SecondHalf_T && counter_turnOverAwayMinus >0)
                    {
                        counter_turnOverAwayMinus--;
                        GoalsAwayMinus.setText(Integer.toString(counter_turnOverAwayMinus));
                    }
                }

            }
        });

        return view;
    }







    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
