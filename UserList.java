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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class UserList extends AppCompatActivity
{
    ListView lst_users;
    BackendlessUser user;
    Intent intent;

    AdminAdapter adminAdapter;
    ArrayAdapter<BackendlessUser> adapter;
    String userfound,Name,role,objectid;
    ProgressDialog progress;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  User List");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        user = new BackendlessUser();

        lst_users = (ListView) findViewById(R.id.UsersList);

        lst_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                intent = new Intent(UserList.this,Roles.class);
                intent.putExtra("name",adminAdapter.getItem(i).getProperty("name").toString());
                intent.putExtra("role",adminAdapter.getItem(i).getProperty("role").toString());
                intent.putExtra("objectId",adminAdapter.getItem(i).getObjectId());
                startActivity(intent);
            }
        });

        getUsers();

    }

    public void getUsers()
    {

        String where = "name !='null'";

        DataQueryBuilder builder = DataQueryBuilder.create();
        builder.setWhereClause(where);
        builder.setPageSize(100).setOffset(0);
        builder.addSortBy("name");

      showProgress(true);
      tvLoad.setText("Getting User List...");

        Backendless.Persistence.of(BackendlessUser.class).find(builder, new AsyncCallback<List<BackendlessUser>>() {
            @Override
            public void handleResponse(List<BackendlessUser> response)
            {
                for (int x =0;x<response.size();x++)
                {
                    userfound =response.get(x).getEmail();
                    Name = response.get(x).getProperty("name").toString();
                    role = response.get(x).getProperty("role").toString();
                    objectid = response.get(x).getObjectId();

                    adminAdapter = new AdminAdapter(UserList.this,response);
                    lst_users.setAdapter(adminAdapter);
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
