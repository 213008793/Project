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

public class PlayersManaged extends AppCompatActivity
{
    TextView textView;
    ListView listView;
    PlayerAdapter playerAdapter;
    Bundle bundle;
    String coach,TeamName,mediNumber,plan,allegies,mediName,cell1,cell2,objectId;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_managed);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  My Players");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();
        listView = (ListView)findViewById(R.id.myPlayers);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                getPlayerInfo();



            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        textView = (TextView)findViewById(R.id.MyPlayersTv);

        coach = Backendless.UserService.CurrentUser().getProperty("name").toString();

        TeamName = bundle.getString("TeamName");

        textView.setText(""+coach+"'s Players");



            //   getTeam();
               getPlayers();

    }

    public void getPlayers()
    {

        String where = "teamName ='" + TeamName.trim() + "'";
        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("playerName");


showProgress(true);
tvLoad.setText("Loading players .......");

        Backendless.Data.of(Player.class).find(builder, new AsyncCallback<List<Player>>() {
            @Override
            public void handleResponse(List<Player> response)
            {
                for (int a = 0 ;a<response.size();a++)
                {

                    playerAdapter = new PlayerAdapter(PlayersManaged.this,response);
                    listView.setAdapter(playerAdapter);
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


    public void getPlayerInfo()
    {
        String where = "teamName ='" + TeamName.trim() + "'";
        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("playerName");



        Backendless.Persistence.of(Player.class).find(builder, new AsyncCallback<List<Player>>() {
            @Override
            public void handleResponse(List<Player> response)
            {
                for (int a = 0 ;a<response.size();a++)
                {
                  String player = response.get(a).getPlayerName();
                  mediName = response.get(a).getMedicalName();
                  mediNumber = response.get(a).getMedicalNumber();
                  allegies = response.get(a).getAllegies();
                  plan = response.get(a).getMedicalPlan();
                  cell1 = response.get(a).getFirstParentCell();
                  cell2 = response.get(a).getSecondParentCell();
                  objectId =response.get(a).getObjectId();

                    Intent intent = new Intent(getApplicationContext(),player_Info.class);
                    intent.putExtra("name", mediName);
                    intent.putExtra("allegies", allegies);
                    intent.putExtra("plan",plan );
                    intent.putExtra("cell1",cell1);
                    intent.putExtra("cell2",cell2);
                    intent.putExtra("mediNum",mediNumber);
                    intent.putExtra("player",player);
                    intent.putExtra("objectId",objectId);
                    startActivity(intent);

                }
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                showtoast(fault.getMessage());


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
