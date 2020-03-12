package com.example.pc.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class StatsActs extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    TextView turnOverHomeAdd,turnOverAwayAdd,TvturnOverHomeMinus,turnOverAwayMinus,GoalsAddHome,
            GoalsAwayAdd,GoalsHomeMinus,GoalsAwayMinus,score1,score2,score3,score4,score5,score6;

    String goalHome1,goalHome2,goalAway1,goalAway2,
            turnOverHome1,turnOverHome2,turnOverAway1,turnOverAway2;




    private ViewPager mViewPager;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_acts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        value = getIntent().getExtras().getString("MatchName");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle(value);
        actionBar.setSubtitle("The Score");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        turnOverHomeAdd = (TextView) findViewById(R.id.turnOverVsFirstCount);//1st half
        turnOverAwayAdd = (TextView)findViewById(R.id.turnOverTeamSecondCount);//scnd Half
        TvturnOverHomeMinus = (TextView)findViewById(R.id.FirstCount);//1
        turnOverAwayMinus = (TextView)findViewById(R.id.turnOverVsSecondCount);//2

        GoalsAddHome = (TextView)findViewById(R.id.goalsTeamFirstCounter);//1
        GoalsHomeMinus = (TextView)findViewById(R.id.goalsTeamSecondCounter);//2
        GoalsAwayAdd = (TextView)findViewById(R.id.goalsVsFirstCounter);//1
        GoalsAwayMinus = (TextView)findViewById(R.id.VsSecondGoals);//2

        score1 = (TextView) findViewById(R.id.score1);
        score2= (TextView) findViewById(R.id.score2);
        score3 = (TextView) findViewById(R.id.score3);
        score4= (TextView) findViewById(R.id.score4);
        score5 = (TextView) findViewById(R.id.score5);
        score6= (TextView) findViewById(R.id.score6);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats_acts, menu);
        return true;
    }



    public void save()
    {
        MatchStatistics statistics = new MatchStatistics();

        statistics.setGame_Name(value);
//        statistics.setTurnOverHome1st(turnOverHomeAdd.getText().toString());
//        statistics.setTurnOverhome2nd( turnOverAwayAdd.getText().toString());
//        statistics.setTurnOverAway1st(TvturnOverHomeMinus.getText().toString());
//        statistics.setTurnOverAway2nd( turnOverAwayMinus.getText().toString());
//
//        statistics.setGoalsHome1st( GoalsAddHome.getText().toString());
//        statistics.setGoalsHome2nd(GoalsHomeMinus.getText().toString());
//        statistics.setGoalsAway1st( GoalsAwayAdd.getText().toString());
//        statistics.setGoalsAway2nd(  GoalsAwayMinus.getText().toString());
//
//        statistics.setCirclePenatrations1st(score1.getText().toString());
//        statistics.setCirclePenatrations2nd(score2.getText().toString());
//        statistics.setPenalty_Coners1st(score3.getText().toString());
//        statistics.setPenalty_Coners2nd(score4.getText().toString());
//
//        statistics.setGoal_shots1st(score5.getText().toString());
//        statistics.setGoal_shots2nd(score6.getText().toString());


        Backendless.Data.save(statistics, new AsyncCallback<MatchStatistics>() {
        @Override
        public void handleResponse(MatchStatistics response)
        {

            showtoast("Match stats saved");


        }

        @Override
        public void handleFault(BackendlessFault fault)
        {
            showtoast(fault.getMessage());
        }
    });

    }



    public void showtoast(String message)
    {
        View view = getLayoutInflater().inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toastContainer));

        TextView  text = (TextView)view.findViewById(R.id.toastView);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings)
        {
            save();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_stats_acts, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:

                    TurnOver_goals turnOver_goals = new TurnOver_goals();

                    return turnOver_goals;


                case 1:

                    MatchStats stats = new MatchStats();

                    return stats;


                case 2:

                    PenaltyConers penaltyConers = new PenaltyConers();
                    return penaltyConers;



                case 3:

                    GoalShots goalShots = new GoalShots();
                   return goalShots;

                default:
                    return null;
            }







        }





        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }



    }



}
