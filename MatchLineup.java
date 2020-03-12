package com.example.pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MatchLineup extends AppCompatActivity
{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_lineup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Matches Menu");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);





    }

    public void matchLine(View view)
    {
        Intent intent = new Intent(MatchLineup.this,Matches.class);
        startActivity(intent);

    }




}
