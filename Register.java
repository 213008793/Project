package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;

import java.util.ArrayList;
import java.util.Date;

public class Register extends AppCompatActivity {
    EditText name, email, password, confirmpass;
    String Name, Email, Password, ConfirmPass;
    Date date;
    TextView text;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        ActionBar actionBar = getSupportActionBar();
        //    actionBar.setIcon(R.mipmap.hockey);
        actionBar.setTitle("  Register");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    public void RegisterDevice()
    {
        ArrayList<String> channels = new ArrayList<String>();
        channels.add("Admin");
        channels.add("Coach");

        Backendless.Messaging.registerDevice("332195978006", channels, date, new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response)
            {
                showtoast("Device Registered ");
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {

                showtoast(fault.getMessage());

            }
        });
    }

    public void send()
    {
        String message = "New User Added"+
                "#Assign Role"+
                "#ASAP!!!"+
                "#Have a good Day";

        PublishOptions pb = new PublishOptions();
        pb.putHeader("android-ticker-text","New User>>");
        pb.putHeader("android-content-title","Admin");
        pb.putHeader("android-content-text","Assign Role");

        Backendless.Messaging.publish("Admin", message, pb, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus messageStatus)
            {




                showtoast("Sent ");

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                showtoast(backendlessFault.getMessage());

            }
        });

    }

    public void Register(View view)
    {
        name = (EditText) findViewById(R.id.Name);
        email = (EditText) findViewById(R.id.Registeremail);
        password = (EditText) findViewById(R.id.RegisterPass);
        confirmpass = (EditText) findViewById(R.id.PassConfirm);
        Name = HelperService.getText(name);
        Email = HelperService.getText(email);
        Password = HelperService.getText(password);
        ConfirmPass = HelperService.getText(confirmpass);


        if (Name.equals("") || Email.equals("") ||
                Password.equals("") || ConfirmPass.equals("")) {

            showtoast("Enter All Data ");


        } else if (!Password.equals(ConfirmPass)) {

            showtoast("Passwords do not Match ");

        } else {

            BackendlessUser backendlessUser = new BackendlessUser();
            backendlessUser.setProperty("email", Email);
            backendlessUser.setProperty("name", Name);
            backendlessUser.setProperty("role", "none");
            backendlessUser.setPassword(ConfirmPass);
            showProgress(true);
            tvLoad.setText("Registering user......");
            Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>()
            {
                @Override
                public void handleResponse(BackendlessUser response)
                {
                  RegisterDevice();
                    showtoast("User Registered Successfully ");

          send();
                    showProgress(false);
                   ClearViews();
                   Register.this.finish();

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    showtoast(fault.getMessage());
                    showProgress(false);

                }
            });
        }
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
        HelperService.clearViews(name,password,confirmpass,email);
    }
}







