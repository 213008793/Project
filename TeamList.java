package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class TeamList extends AppCompatActivity
{
    ListView lst_users;
    Team team;
    TeamAdapter teamAdapter;
    ProgressDialog progress;
    Intent intent;
    String coach,mediName,mediNumber,plan,Allegies;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        team = new Team();
        lst_users = (ListView) findViewById(R.id.TeamList);

        lst_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {




            }
        });

        coach = Backendless.UserService.CurrentUser().getProperty("name").toString();

        getTeam();


    }

    //String where = "coach = '" + coach.trim() +"'"


    public void getTeam()
    {

      String where = "coach != 'null'" ;    // change to current couch

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);


        showProgress(true);
        tvLoad.setText("Getting Team List");

        Backendless.Persistence.of(Team.class).find(builder, new AsyncCallback<List<Team>>() {
            @Override
            public void handleResponse(List<Team> response)

            {for(int x =0;x<response.size();x++)

                {
                    teamAdapter = new TeamAdapter(TeamList.this,response);
                    lst_users.setAdapter(teamAdapter);
                    showProgress(false);
                }


            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                showtoast(fault.getMessage());
                showProgress(false);
            }
        });
    }


    public void GetPlayers()
    {
        String where ="teamName ='pirates'"; // get team name of coach

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        // builder.addSortBy("playerName");


        Backendless.Persistence.of(Player.class).find(builder, new AsyncCallback<List<Player>>() {
            @Override
            public void handleResponse(List<Player> response)
            {
                for(int x =0;x<response.size();x++)
                {
                  mediName = response.get(x).getMedicalName();
                  mediNumber = response.get(x).getMedicalNumber();
                  plan = response.get(x).getMedicalPlan();
                  Allegies = response.get(x).getAllegies();
                }


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

        TextView text = (TextView)view.findViewById(R.id.toastView);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
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

}
