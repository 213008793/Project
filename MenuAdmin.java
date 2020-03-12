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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MenuAdmin extends AppCompatActivity {

    Button coach,roles,MovePlayer,AddTeam;
    TextView User,tv_Role;
    String role;
    BackendlessUser user;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Admin");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);




        String value = getIntent().getExtras().getString("email");
        role =getIntent().getExtras().getString("role");
        User =(TextView)findViewById(R.id.loggedUser);
        User.setText(value);
        tv_Role = (TextView)findViewById(R.id.tv_Role);
        tv_Role.setText(role);
        coach = (Button)findViewById(R.id.CoachMenu);
        roles = (Button)findViewById(R.id.setRoles);
        MovePlayer = (Button)findViewById(R.id.players);
        AddTeam = (Button)findViewById(R.id.AddTeam);


        user = Backendless.UserService.CurrentUser();

        Buttoncontrol();

        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuAdmin.this,Coachmenu.class);
                startActivity(intent);
            }
        });

        roles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAdmin.this,UserList.class);
                startActivity(intent);
            }
        });

        MovePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuAdmin.this,PlayerList.class);
                startActivity(intent);

            }
        });

        AddTeam.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {

        Intent intent = new Intent(MenuAdmin.this,Team_Opponent.class);
        startActivity(intent);


    }
});
    }


    public void Buttoncontrol() {

        if (user.getProperty("role").equals("Coach"))

        {
            MovePlayer.setVisibility(View.GONE);
            roles.setVisibility(View.GONE);

        }
        else if(user.getProperty("role").equals("Admin"))
        {
            coach.setVisibility(View.GONE);
        }

        else if (user.getProperty("role").equals("none"))
        {
            AddTeam.setVisibility(View.GONE);
            MovePlayer.setVisibility(View.GONE);
            roles.setVisibility(View.GONE);
            coach.setVisibility(View.GONE);

            showtoast("Do not Have Access to the System");


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.close:


                showProgress(true);
                tvLoad.setText("Logging out....");
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response)
                    {

                        showtoast("Logged out Succesfully");
                        showProgress(false);
                         MenuAdmin.this.finish();

                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        showtoast(fault.getMessage());
                        showProgress(false);

                    }
                });


                break;
        }
        return super.onOptionsItemSelected(item);
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
