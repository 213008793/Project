package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class MovePlayer extends AppCompatActivity {

EditText TeamName;
Spinner MoveSpinner;
TextView CurrentTeamName,playerName;
    String name,Name, objectId,id;
    private View mProgressView;
    Bundle bundle;
    private View mLoginFormView;
    private TextView tvLoad;

    final List<String> team_name = new ArrayList<>();

    ArrayAdapter<String>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_player);

        bundle = getIntent().getExtras();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Move Player");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

         name = bundle.getString("PlayerName");
         Name = bundle.getString("TeamName");
         id = bundle.getString("ID");

        playerName = (TextView) findViewById(R.id.edtPlayerName);
        CurrentTeamName = (TextView) findViewById(R.id.tvCurrentTeam);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        MoveSpinner = findViewById(R.id.MoveSpinner);


        playerName.setText( bundle.getString("PlayerName"));
        CurrentTeamName.setText(bundle.getString("TeamName"));
//        getPlayers();

        getTeam();

    }

    public void MovePlayers(View view)
    {




        Player player = new Player();



        player.setTeamName(MoveSpinner.getSelectedItem().toString());
        player.setObjectId(id);


            showProgress(true);
            tvLoad.setText("Moving player.....");
            Backendless.Persistence.save(player, new AsyncCallback<Player>() {
                @Override
                public void handleResponse(Player response) {
                    showProgress(false);
                    showtoast("Player moved to  " + MoveSpinner.getSelectedItem().toString());

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    showtoast(fault.getMessage());
                    showProgress(false);

                }
            });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moveplayer, menu);
        return super.onCreateOptionsMenu(menu);
    }


        @Override
        public boolean onOptionsItemSelected (MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.Itemteam:

                    Intent intent = new Intent(MovePlayer.this, TeamList.class);
                    startActivity(intent);

                    break;
            }
            return super.onOptionsItemSelected(item);
        }



    public void getTeam() {

        String where = "teamName != null  ";

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

                    String name = team.getTeamName();
                    team_name.add(name);
                    arrayAdapter = new ArrayAdapter<String>(MovePlayer.this,
                            android.R.layout.simple_spinner_item, team_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    MoveSpinner.setPrompt("Choose Team");
                    MoveSpinner.setAdapter(arrayAdapter);
                }


            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                HelperService.showToast(MovePlayer.this,fault.getMessage());
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
