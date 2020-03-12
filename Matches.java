package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Matches extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    Spinner Opponenes,Team,player1,player2,player3,player4,player5
            ,player6,player7,player8,player9,player10,
            player11,bench5,bench2,bench3,bench4;

    RatingBar playerOne,playerTwo,playerThree,playerfour,playerSeven,
            playerSix,playerFive,playerEight,playernine,playerten,
            playereleven,benchOne,benchtwo,benchthree,benchFour;

    EditText playerOne_goal,playerTwo_goal,playerThree_goal,playerfour_goal,playerfive_goal,
            playerSix_goal,playerseven_goals,playerEight_goal,playernine_goal,
            playerten_goal,
            playereleven_goal,benchOne_goal,benchtwo_goal,benchthree_goal,benchFour_goal;

    TextView teams,Matches;
    ListView lst_users;
    String name,TeamsSelected;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;



    ArrayAdapter<String>arrayAdapter;
    String coach, TeamName;
    String matchName;

    final List<String> team_name = new ArrayList<>();
    final List<String> opponent_name = new ArrayList<>();
    final List<String> player_name = new ArrayList<>();
    final List<String> match_name = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);


        Opponenes =(Spinner)findViewById(R.id.spinnerOpponents);
        Team=(Spinner)findViewById(R.id.spinnerTeams);

        Team.setOnItemSelectedListener(this);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);





        player1 = (Spinner)findViewById(R.id.player_left_forward);
        player2 =  (Spinner)findViewById(R.id.player_right_forward);
        player3 =  (Spinner)findViewById(R.id.player_center_forward);
        player4 =  (Spinner)findViewById(R.id.player_left_link);
        player5 = (Spinner)findViewById(R.id.player_right_link);
        player6 =  (Spinner)findViewById(R.id.player_center_link);
        player7 =  (Spinner)findViewById(R.id.player_left_back);
        player8 =  (Spinner)findViewById(R.id.player_right_back);
        player9 =  (Spinner)findViewById(R.id.player_center_back);
        player10 =  (Spinner)findViewById(R.id.player_sweeper);
        player11 =  (Spinner)findViewById(R.id.player_goalie);
        bench5 =  (Spinner)findViewById(R.id.playerBench2);
        bench2 =  (Spinner)findViewById(R.id.playerBench3);
        bench3 =  (Spinner)findViewById(R.id.playerBench4);
        bench4 =  (Spinner)findViewById(R.id.playerBench5);

        playerOne = (RatingBar)findViewById(R.id.ratingLeftForward);
        playerTwo = (RatingBar)findViewById(R.id.ratingRightForward);
        playerThree =(RatingBar)findViewById(R.id.ratingCenterForward);
        playerfour =(RatingBar)findViewById(R.id.ratingLeftLink);
        playerFive = (RatingBar)findViewById(R.id.ratingRightLink);
        playerSix = (RatingBar)findViewById(R.id.ratingCenterLink);
        playerSeven = (RatingBar)findViewById(R.id.ratingLeftBack);
        playerEight =(RatingBar)findViewById(R.id.ratingRightBack);
        playernine = (RatingBar)findViewById(R.id.ratingCenterBack);
        playerten = (RatingBar)findViewById(R.id.ratingSweeper);
        playereleven = (RatingBar)findViewById(R.id.ratingGoalie);
        benchOne =(RatingBar)findViewById(R.id.ratingBench2);
        benchtwo =(RatingBar)findViewById(R.id.ratingBench3);
        benchthree = (RatingBar)findViewById(R.id.ratingBench4);
        benchFour = (RatingBar)findViewById(R.id.ratingBench5);


        playerOne_goal = (EditText)findViewById(R.id.edtGoalLeftForward);
        playerTwo_goal = (EditText)findViewById(R.id.edtRightForward);
        playerThree_goal = (EditText)findViewById(R.id.edtGoalCenterForward);
        playerfour_goal = (EditText)findViewById(R.id.edtGoalsLeftLink);
        playerfive_goal= (EditText)findViewById(R.id.edtGoalsRightLink);
        playerSix_goal= (EditText)findViewById(R.id.edtGoalsCenterLink);
        playerseven_goals= (EditText)findViewById(R.id.edtGoalsLeftBack);
        playerEight_goal = (EditText)findViewById(R.id.edtGoalsRightBack);
        playernine_goal = (EditText)findViewById(R.id.edtGoalsCenterBack);
        playerten_goal = (EditText)findViewById(R.id.edtGoalsSweeper);
        playereleven_goal = (EditText)findViewById(R.id.edtGoalsGoalie);
        benchOne_goal = (EditText)findViewById(R.id.edtGoalsBench2);
        benchtwo_goal = (EditText)findViewById(R.id.edtGoalsBench3);
        benchthree_goal = (EditText)findViewById(R.id.edtGoalsBench4);
        benchFour_goal = (EditText)findViewById(R.id.edtGoalsBench5);


        coach = Backendless.UserService.CurrentUser().getProperty("name").toString();





getOpponent();
getTeam();











    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
       TeamsSelected = adapterView.getItemAtPosition(i).toString();
        getPlayers();
        Toast.makeText(com.example.pc.myapplication.Matches.this, TeamsSelected, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }


    public void getTeam() {

        String where = "coach = '" + coach.trim() + "'";

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);




        Backendless.Persistence.of(Team.class).find(builder, new AsyncCallback<List<Team>>() {
            @Override
            public void handleResponse(List<Team> response)

            {
                Iterator<Team> teamIterator = response.iterator();
                while (teamIterator.hasNext())
                {
                    com.example.pc.myapplication.Team team = teamIterator.next();

                    name = team.getTeamName();
                    team_name.add(name);
                    arrayAdapter = new ArrayAdapter<String>(com.example.pc.myapplication.Matches.this,
                            android.R.layout.simple_spinner_item, team_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Team.setAdapter(arrayAdapter);




                }


            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                HelperService.showToast(com.example.pc.myapplication.Matches.this,fault.getMessage());
            }
        });
    }


    public void getOpponent()
    {
        String where = "teamName !='null'";

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("teamName");


        Backendless.Persistence.of(Opponent.class).find(builder, new AsyncCallback<List<Opponent>>() {
            @Override
            public void handleResponse(List<Opponent> response)
            {
                Iterator<Opponent> OpponentIterator = response.iterator();
                while (OpponentIterator.hasNext())
                {
                    Opponent opponent = OpponentIterator.next();
                    String name = opponent.getTeamName();
                    opponent_name.add(name);
                    arrayAdapter = new ArrayAdapter<String>(com.example.pc.myapplication.Matches.this,
                            android.R.layout.simple_spinner_item, opponent_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Opponenes.setAdapter(arrayAdapter);



                }

            }

            @Override
            public void handleFault(BackendlessFault fault)
            {

                HelperService.showToast(com.example.pc.myapplication.Matches.this,fault.getMessage());

            }
        });
    }

    public void getPlayers()
    {
        String where =  "teamName = '" + TeamsSelected + "'";

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("playerName");


        Backendless.Persistence.of(Player.class).find(builder, new AsyncCallback<List<Player>>() {
            @Override
            public void handleResponse(List<Player> response)
            {
                Iterator<Player> OpponentIterator = response.iterator();
                while (OpponentIterator.hasNext())
                {
                    Player player = OpponentIterator.next();
                    String name = player.getPlayerName();
                    player_name.add(name);
                    arrayAdapter = new ArrayAdapter<String>(com.example.pc.myapplication.Matches.this,
                            android.R.layout.simple_spinner_item, player_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    player3.setAdapter(arrayAdapter);
                    player1.setAdapter(arrayAdapter);
                    player2.setAdapter(arrayAdapter);
                    player4.setAdapter(arrayAdapter);
                    player5.setAdapter(arrayAdapter);
                    player6.setAdapter(arrayAdapter);
                    player7.setAdapter(arrayAdapter);
                    player8.setAdapter(arrayAdapter);
                    player9.setAdapter(arrayAdapter);
                    player10.setAdapter(arrayAdapter);
                    player11.setAdapter(arrayAdapter);
                    bench5.setAdapter(arrayAdapter);
                    bench2.setAdapter(arrayAdapter);
                    bench3.setAdapter(arrayAdapter);
                    bench4.setAdapter(arrayAdapter);

                }

            }

            @Override
            public void handleFault(BackendlessFault fault)
            {

                HelperService.showToast(com.example.pc.myapplication.Matches.this,fault.getMessage());

            }
        });
    }


    public void getMatch()
    {
        String where = "awayTeam = '" + Opponenes.getSelectedItem().toString() + "'"+"homeTeam = '" +Team.getSelectedItem().toString() + "'";

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("homeTeam");


        Backendless.Persistence.of(Match.class).find(builder, new AsyncCallback<List<Match>>() {
            @Override
            public void handleResponse(List<Match> response)
            {

                Iterator<Match> OpponentIterator = response.iterator();
                while (OpponentIterator.hasNext())
                {


                }

            }

            @Override
            public void handleFault(BackendlessFault fault)
            {



            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    { getMenuInflater().inflate(R.menu.matches, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.saveMatch:
Savematch();
                break;


            case R.id.saveMatchStats:


                Intent intent = new Intent(Matches.this ,MatchList.class);
                intent.putExtra("Team",TeamsSelected);
                startActivity(intent);
                break;
        }




        return super.onOptionsItemSelected(item);
    }


    public void Savematch()
    {
       // use TeamsSelected

        String opponent = Opponenes.getSelectedItem().toString();
        String player_name_one = player1.getSelectedItem().toString();
        String player_name_two = player2.getSelectedItem().toString();
        String player_name_three = player3.getSelectedItem().toString();
        String player_name_four= player4.getSelectedItem().toString();
        String player_name_five = player5.getSelectedItem().toString();
        String player_name_six = player6.getSelectedItem().toString();
        String player_name_seven = player7.getSelectedItem().toString();
        String player_name_eight = player8.getSelectedItem().toString();
        String player_name_nine = player9.getSelectedItem().toString();
        String player_name_ten = player10.getSelectedItem().toString();
        String player_name_eleven = player11.getSelectedItem().toString();
        String player_name_bentch1 = bench5.getSelectedItem().toString();
        String player_name_bentch2 = bench2.getSelectedItem().toString();
        String player_name_bentch3 = bench3.getSelectedItem().toString();
        String player_name_bentch4 = bench4.getSelectedItem().toString();


        double player1Rate = playerOne.getRating();
        double player2Rate = playerTwo.getRating();
        double player3Rate = playerThree.getRating();
        double player4Rate = playerfour.getRating();
        double player5Rate = playerFive.getRating();
        double player6Rate = playerSix.getRating();
        double player7Rate = playerSeven.getRating();
        double player8Rate = playerEight.getRating();
        double player9Rate = playernine.getRating();
        double player10Rate = playerten.getRating();
        double player11Rate = playereleven.getRating();
        double bentch1Rate = benchOne.getRating();
        double bentch2Rate = benchtwo.getRating();
        double bentch3Rate = benchthree.getRating();
        double bentch4Rate = benchFour.getRating();

        String player1Goal = HelperService.getText(playerOne_goal);
        String player2Goal = HelperService.getText(playerTwo_goal);
        String player3Goal = HelperService.getText(playerThree_goal);
        String player4Goal = HelperService.getText(playerfour_goal);
       String player5Goal = HelperService.getText(playerfive_goal);
        String player6Goal = HelperService.getText(playerSix_goal);
        String player7Goal = HelperService.getText(playerseven_goals);
        String player8Goal = HelperService.getText(playerEight_goal);
        String player9Goal = HelperService.getText(playernine_goal);
        String player10Goal = HelperService.getText(playerten_goal);
        String player11Goal = HelperService.getText(playerEight_goal);
        String bench1Goal = HelperService.getText(benchOne_goal);
        String bench2Goal = HelperService.getText(benchtwo_goal);
        String bench3Goal = HelperService.getText(benchthree_goal);
        String bench4Goal = HelperService.getText(benchFour_goal);


        if(!HelperService.hasText(playerOne_goal,playerTwo_goal,playerThree_goal,playerfour_goal,playerfive_goal,playerSix_goal,playerseven_goals,playerEight_goal,playernine_goal,playerten_goal,playereleven_goal,benchOne_goal,benchtwo_goal,benchthree_goal,benchthree_goal))
        {
            Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show();

        }

        else
            {


                Match match = new Match();
                match.setMatchName(opponent+ "'vs'"+TeamsSelected);
                match.setAwayTeam(opponent);
                match.setHomeTeam(TeamsSelected);
                match.setLeft_Forward(player_name_one);
                match.setRight_Forward(player_name_two);
                match.setCenter_Forward(player_name_three);
                match.setLeft_Link(player_name_four);
                match.setRight_Link(player_name_five);
                match.setCenter_Link(player_name_six);
                match.setLeft_Back(player_name_seven);
                match.setRight_Back(player_name_eight);
                match.setCenter_Back(player_name_nine);
                match.setSweeper(player_name_ten);
                match.setGoalie(player_name_eleven);
                match.setBench1(player_name_bentch1);
                match.setBench2(player_name_bentch2);
                match.setBench3(player_name_bentch3);
                match.setBench4(player_name_bentch4);




                match.setLeft_Forward_rating(player1Rate);
                match.setRight_Forward_rating(player2Rate);
                match.setCenter_Forward_rating(player3Rate);
                match.setLeft_Link_rating(player4Rate);
                match.setRight_Link_rating(player5Rate);
                match.setCenter_Link_rating(player6Rate);
                match.setLeft_Back_rating(player7Rate);
                match.setRight_Back_rating(player8Rate);
                match.setCenter_Back_rating(player9Rate);
                match.setSweeper_rating(player10Rate);
                match.setGoalie_rating(player11Rate);
                match.setBench1_rating(bentch1Rate);
                match.setBench2_rating(bentch2Rate);
                match.setBench3_rating(bentch3Rate);
                match.setBench4_rating(bentch4Rate);


                match.setLeft_Forward_goals(player1Goal);
                match.setRight_Forward_goals(player2Goal);
                match.setCenter_Back_goals(player3Goal);
                match.setLeft_Link_goals(player4Goal);
                match.setRight_Link_goals(player5Goal);
                match.setCenter_Link_goals(player6Goal);
                match.setLeft_Back_goals(player7Goal);
                match.setRight_Back_goals(player8Goal);
                match.setCenter_Back_goals(player9Goal);
                match.setSweeper_goals(player10Goal);
                match.setGoalie_goals(player11Goal);
                match.setBench1_goals(bench1Goal);
                match.setBench2_goals(bench2Goal);
                match.setBench3_goals(bench3Goal);
                match.setBench4_goals(bench4Goal);

                showProgress(true);
                tvLoad.setText("Saving match...");
                Backendless.Persistence.save(match, new AsyncCallback<Match>() {
                    @Override
                    public void handleResponse(Match response)
                    {

                       showtoast("Match Saved");
                        showProgress(false);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        showProgress(false);
                        showtoast(fault.getMessage());
                    }
                });

        }


    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void showtoast(String message)
    {
        View view = getLayoutInflater().inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toastContainer));

        TextView text = (TextView)view.findViewById(R.id.toastView);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }


}
