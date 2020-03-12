package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatchList extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    ArrayAdapter arrayAdapter;
    final List<String> match_name = new ArrayList<>();

    Spinner spinner;
    Match match;
    String value,name;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        spinner = (Spinner)findViewById(R.id.spinnerMatchList);
        spinner.setOnItemSelectedListener(this);

        value = getIntent().getExtras().getString("Team");

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


        getMatch();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Intent intent = new Intent(MatchList.this,StatsActs.class);
        intent.putExtra("MatchName",spinner.getSelectedItem().toString());
        startActivity(intent);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void getMatch()
    {
        String where = "homeTeam = '" + value + "'";

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("homeTeam");
showProgress(true);
tvLoad.setText("Loading Match List...");
        Backendless.Persistence.of(Match.class).find(builder, new AsyncCallback<List<Match>>() {
            @Override
            public void handleResponse(List<Match> response)
            {
                Iterator<Match> MatchIterator = response.iterator();
                while (MatchIterator.hasNext())
                {
                    Match match = MatchIterator.next();
                     name = match.getMatchName();
                    match_name.add(name);
                    arrayAdapter = new ArrayAdapter<String>(MatchList.this,
                            android.R.layout.simple_spinner_item, match_name);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
showProgress(false);


                }

            }

            @Override
            public void handleFault(BackendlessFault fault)
            {

                HelperService.showToast(MatchList.this,fault.getMessage());

                showProgress(false);

            }
        });
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
