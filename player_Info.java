package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class player_Info extends AppCompatActivity {

    String firstCell, SecondCell, Name, Num, Alleg, plans, medNa, objectid;
    EditText name, Mediname, MediNum, Plan, allegies;
    Intent intent;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player__info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Player Info");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        name = (EditText) findViewById(R.id.PlName);
        Mediname = (EditText) findViewById(R.id.PlmediName);
        MediNum = (EditText) findViewById(R.id.PlmediNum);
        Plan = (EditText) findViewById(R.id.PlmediPlan);
        allegies = (EditText) findViewById(R.id.PlAllegies);
        medNa = getIntent().getExtras().getString("name");
        Alleg = getIntent().getExtras().getString("allegies");
        plans = getIntent().getExtras().getString("plan");
        Num = getIntent().getExtras().getString("mediNum");
        Name = getIntent().getExtras().getString("player");
        firstCell = getIntent().getExtras().getString("cell1");
        SecondCell = getIntent().getExtras().getString("cell2");
        objectid = getIntent().getExtras().getString("objectId");


        Plan.setText(plans);
        MediNum.setText(Num);
        Mediname.setText(medNa);
        allegies.setText(Alleg);
        name.setText(Name);

        Plan.setEnabled(false);
        Mediname.setEnabled(false);
        MediNum.setEnabled(false);
        allegies.setEnabled(false);
        name.setEnabled(false);


    }


    public void callParent1(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + firstCell));
        startActivity(intent);

    }

    public void callParent2(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + SecondCell));
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player_info, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:

                SendData();
                break;


            case R.id.delete:

                DeletePlayer();
                break;
        }
        return super.onOptionsItemSelected(item);

    }





    public void SendData() {
        intent = new Intent(getApplicationContext(), EditPlayer.class);
        intent.putExtra("medicalName", medNa);
        intent.putExtra("medicalNumber", Num);
        intent.putExtra("plan", plans);
        intent.putExtra("allegies", Alleg);
        intent.putExtra("cell1", firstCell);
        intent.putExtra("cell2", SecondCell);
        intent.putExtra("playerName", Name);
        intent.putExtra("object", objectid);
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

    public void DeletePlayer() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Once Deleted can not Access player again");
        alertDialogBuilder.setTitle("Delete Player");
        alertDialogBuilder.setIcon(R.mipmap.taxi_driver);
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        Player player = new Player();

                        player.setObjectId(objectid);

                        Backendless.Persistence.of(Player.class).remove(player, new AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long response)
                            {
                                showtoast("Player Deleted");
                            }

                            @Override
                            public void handleFault(BackendlessFault fault)
                            {

                                showtoast(fault.getMessage());
                            }
                        });

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
