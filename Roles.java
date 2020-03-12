package com.example.pc.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Roles extends AppCompatActivity
{
    Spinner selectRole;
    TextView tvViewUser, tvRole;
    BackendlessUser user;
    Bundle bundle;
    String objectId,Name,Role,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("User Roles");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        tvViewUser = (TextView) findViewById(R.id.tvViewUser);
        tvRole = (TextView) findViewById(R.id.tvRole);
        selectRole = (Spinner) findViewById(R.id.roleSpinner);

        bundle = getIntent().getExtras();



      Name = bundle.getString("name");
      Role =bundle.getString("role");
      id = bundle.getString("objectId");

        tvViewUser.setText(Name);
        tvRole.setText(Role);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.UserRoles, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectRole.setAdapter(adapter);
    }

    public void SubmitRole(View view)
    {
        final String role = selectRole.getSelectedItem().toString();

                user = new BackendlessUser();
                user.setProperty("role",role);
                user.setProperty("objectId",id);

                Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response)
                    {
                        showtoast("Role Updated");

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

        TextView text = (TextView)view.findViewById(R.id.toastView);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

}
