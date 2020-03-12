package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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

public class AddPlayer extends AppCompatActivity {

    EditText playerName,  playerSurname;
    ProgressDialog progress;
    String ObjectId;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    Spinner teamFor;

    final List<String> team_name = new ArrayList<>();

    ArrayAdapter<String>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Add Player");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        teamFor = (Spinner)findViewById(R.id.spinnerPlayingFor);
        playerName = (EditText) findViewById(R.id.edtPlayer);
        playerSurname = (EditText) findViewById(R.id.edtSurname);

        getTeam();
    }

    public void SavePlayer(View view) {
        String name = HelperService.getText(playerName);

        String surname = HelperService.getText(playerSurname);
        if (name.isEmpty() || teamFor.getSelectedItem().toString().trim().isEmpty() || surname.isEmpty()) {
            showtoast("Enter All Data");

        } else {

            Player player = new Player();

            player.setPlayerName(HelperService.getText(playerName));
            player.setSurname(HelperService.getText(playerSurname));
            player.setTeamName(teamFor.getSelectedItem().toString().trim());
            player.setAllegies("none");
            player.setFirstParentCell("none");
            player.setMedicalName("none");
            player.setMedicalNumber("none");
            player.setMedicalPlan("none");
            player.setSecondParentCell("none");
            player.setGoalsScored(0);

            showProgress(true);
            tvLoad.setText("Adding Player......");

            Backendless.Persistence.save(player, new AsyncCallback<Player>() {
                @Override
                public void handleResponse(Player response) {
                    showtoast("Player Added");
                    ClearView();

                    ObjectId = response.getObjectId();
                    showProgress(false);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    showtoast(fault.getMessage());
                    ClearView();
                    showProgress(false);
                }
            });
        }

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
                    arrayAdapter = new ArrayAdapter<String>(AddPlayer.this,
                            android.R.layout.simple_spinner_item, team_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    teamFor.setPrompt("Choose Coach");
                    teamFor.setAdapter(arrayAdapter);
                }


            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                HelperService.showToast(AddPlayer.this,fault.getMessage());
            }
        });
    }



    public void MedicalInfo(View view) {
        Intent intent = new Intent(this, medicalInfo.class);
        intent.putExtra("name", HelperService.getText(playerName));
        intent.putExtra("team",teamFor.getSelectedItem().toString().trim() );
        intent.putExtra("surname", HelperService.getText(playerSurname));
        ClearView();
        startActivity(intent);
    }

    public void showtoast(String message) {
        View view = getLayoutInflater().inflate(R.layout.toast,
                (ViewGroup) findViewById(R.id.toastContainer));

        TextView text = (TextView) view.findViewById(R.id.toastView);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
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

    public void ClearView()

    {
        HelperService.clearViews( playerName,playerSurname);
    }
}
