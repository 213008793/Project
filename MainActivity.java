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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

public class MainActivity extends AppCompatActivity
{
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    String password,email,role;
    EditText Email,Password;
    Button coach,roles,MovePlayer,AddTeam;
    View layout;
    TextView text;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Login");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        AddTeam = (Button)findViewById(R.id.AddTeam);
        Email =(EditText)findViewById(R.id.loginUserNameTxt);
        Password=(EditText)findViewById(R.id.userPasswordTxt);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

//stayLogin();



    }


    public void Register_Click(View view)
    {
        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);

    }

    public void reset(View view)
    {
        email= Email.getText().toString().trim();
        if(email.isEmpty())
        {
            showtoast("Email is Empty");
        }
        else
        {
            showProgress(true);
            tvLoad.setText("Wait while we reset Password....");
            Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                @Override
                public void handleResponse(Void response)
                {
                    showtoast("Link will be sent Shortly via email");
                    showProgress(false);
                    ClearViews();

                }

                @Override
                public void handleFault(BackendlessFault fault)
                {
                    showtoast(fault.getMessage());
                    ClearViews();
                    showProgress(false);
                }
            });
        }
    }

    public void Login(View view)
    {
        email= Email.getText().toString().trim();
        password = Password.getText().toString().trim();

        if(email.isEmpty()||password.isEmpty())
        {
            showtoast("Enter all data");
        }

        else
        {
            showProgress(true);
            tvLoad.setText("Logging User....");
            Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser response)
                {

                    showtoast("Logged in");




                    role = Backendless.UserService.CurrentUser().getProperty("role").toString();
                    Intent intent = new Intent(MainActivity.this,MenuAdmin.class);
                    intent.putExtra("email", HelperService.getText(Email));
                    intent.putExtra("role",role);
                    startActivity(intent);
                    showProgress(false);


                }

                @Override
                public void handleFault(BackendlessFault fault)
                {

                   showtoast(fault.getMessage());
                   ClearViews();
                   showProgress(false);


                }
            },true);
        }

    }

    public void stayLogin()
    {
        showProgress(true);
        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>()
        {
            @Override
            public void handleResponse(Boolean response)
            {
                if(response)
                {
                    String objectId = UserIdStorageFactory.instance().getStorage().get();

                    Backendless.Data.of(BackendlessUser.class).findById(objectId, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response)
                        {

                            startActivity( new Intent(MainActivity.this,MenuAdmin.class));
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault)
                        {
                            showtoast(fault.getMessage());
                            showProgress(false);

                        }
                    });

                }

                else
                {
                    showProgress(false);
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

        text = (TextView)view.findViewById(R.id.toastView);
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



    public void ClearViews()
    {
        HelperService.clearViews(Password,Email);
    }




}


