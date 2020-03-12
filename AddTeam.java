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

public class AddTeam extends AppCompatActivity {


    Spinner ageGroup,CoachSpinner;
    EditText coach,teamName;
    ProgressDialog progress;
    Intent intent;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    final List<String> coach_name = new ArrayList<>();

    ArrayAdapter<String>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Add Team");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        CoachSpinner =(Spinner)findViewById(R.id.CoachSpinner);

        ageGroup = (Spinner)findViewById(R.id.TeamSpinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.AgeGroup, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageGroup.setAdapter(adapter);

        getTeam();

    }

    public void Submit(View view)
    {

        teamName =(EditText)findViewById(R.id.etTeam);

        String name =  HelperService.getText(teamName);

        if (!name.isEmpty()||!ageGroup.getSelectedItem().toString().isEmpty())
        {

            Team team = new Team();

            team.setAgeGroup(ageGroup.getSelectedItem().toString().trim());
            team.setCoach(CoachSpinner.getSelectedItem().toString());
            team.setTeamName(HelperService.getText(teamName));

      showProgress(true);
      tvLoad.setText("Adding Team....");

            Backendless.Persistence.save(team, new AsyncCallback<Team>() {
                @Override
                public void handleResponse(Team response)
                {
                    showtoast("Team Added");
                    ClearView();
                    showProgress(false);

                }

                @Override
                public void handleFault(BackendlessFault fault)
                {
                    showtoast(fault.getMessage());
                    ClearView();
                    showProgress(false);

                }
            });

        }

        else
        {
            showtoast("Enter All Data");
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

                    String name = team.getCoach();
                    coach_name.add(name);
                    arrayAdapter = new ArrayAdapter<String>(AddTeam.this,
                            android.R.layout.simple_spinner_item, coach_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    CoachSpinner.setPrompt("Choose Coach");
                    CoachSpinner.setAdapter(arrayAdapter);
                }


            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                HelperService.showToast(AddTeam.this,fault.getMessage());
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

    public void ClearView()
    {
        HelperService.clearViews( coach,teamName,ageGroup);
    }
}
