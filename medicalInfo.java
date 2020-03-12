package com.example.pc.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

public class medicalInfo extends AppCompatActivity {

    EditText mediName, mediPlan, Allegies, Fistparent, secondParent,
            mediNumber;
    TextView playername;
    Player player;
    String ObjectId, value, surname, team;

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Medical Info");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        mediName = (EditText) findViewById(R.id.edtmediName);
        mediPlan = (EditText) findViewById(R.id.edtMediplan);
        Allegies = (EditText) findViewById(R.id.edtAllegies);
        Fistparent = (EditText) findViewById(R.id.edtfistParentCell);
        secondParent = (EditText) findViewById(R.id.edtSecondParentCell);
        mediNumber = (EditText) findViewById(R.id.edtMediNumber);
        playername = (TextView) findViewById(R.id.tvMediPlayerName);
        value = getIntent().getExtras().getString("name");
        surname = getIntent().getStringExtra("surname");
        team = getIntent().getStringExtra("team");

        playername.setText(value);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.medical, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ItemMedical:

                SaveMedical();

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void SaveMedical()

    {
        String num = HelperService.getText(mediNumber);
        String name =  HelperService.getText(mediName);
        String alleg =   HelperService.getText(Allegies);
        String sec = HelperService.getText(secondParent);
        String fst =HelperService.getText(Fistparent);
        String plan = HelperService.getText(mediPlan);

        if (!num.isEmpty()||!name.isEmpty()||!alleg.isEmpty()||!sec.isEmpty()||!fst.isEmpty()||!plan.isEmpty())
        {


            Player player = new Player();

            player.setPlayerName(value);
            player.setSurname(surname);
            player.setTeamName(team);
            player.setAllegies(HelperService.getText(Allegies));
            player.setFirstParentCell(HelperService.getText(Fistparent));
            player.setMedicalName(HelperService.getText(mediName));
            player.setMedicalNumber(HelperService.getText(mediNumber));
            player.setMedicalPlan(HelperService.getText(mediPlan));
            player.setSecondParentCell(HelperService.getText(secondParent));

            showProgress(true);
            tvLoad.setText("Saving player with medical ");

            Backendless.Data.save(player, new AsyncCallback<Player>() {
                @Override
                public void handleResponse(Player response)
                {
                    showtoast("Player saved with Medical");

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
            showtoast("Enter All Data");
        }
    }


//        public void SaveMedical(View view)
//    {
//   String num = HelperService.getText(mediNumber);
//        String name =  HelperService.getText(mediName);
//        String alleg =   HelperService.getText(Allegies);
//        String sec = HelperService.getText(secondParent);
//        String fst =HelperService.getText(Fistparent);
//        String plan = HelperService.getText(mediPlan);
//
//        if (!num.isEmpty()||!name.isEmpty()||!alleg.isEmpty()||!sec.isEmpty()||!fst.isEmpty()||!plan.isEmpty())
//        {
//
//
//            Player player = new Player();
//
//            player.setPlayerName(value);
//            player.setSurname(surname);
//            player.setTeamName(team);
//            player.setAllegies(HelperService.getText(Allegies));
//            player.setFirstParentCell(HelperService.getText(Fistparent));
//            player.setMedicalName(HelperService.getText(mediName));
//            player.setMedicalNumber(HelperService.getText(mediNumber));
//            player.setMedicalPlan(HelperService.getText(mediPlan));
//            player.setSecondParentCell(HelperService.getText(secondParent));
//
//            Backendless.Data.save(player, new AsyncCallback<Player>() {
//                @Override
//                public void handleResponse(Player response)
//                {
//                    showtoast("Player saved with Medical");
//
//                }
//
//                @Override
//                public void handleFault(BackendlessFault fault)
//                {
//                    showtoast(fault.getMessage());
//
//                }
//            });
//
//        }
//
//        else
//        {
//            showtoast("Enter All Data");
//        }
//    }

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
