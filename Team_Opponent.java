package com.example.pc.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Team_Opponent extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team__opponent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.taxi_driver);
        actionBar.setTitle("  Add team/Opponent");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    public void team(View view)
    {
        Intent intent = new Intent(Team_Opponent.this,AddTeam.class);
        startActivity(intent);
    }

    public void player(View view)
    {
        Intent intent = new Intent(Team_Opponent.this,AddOpponent.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.teamopponentlists,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.tea:

                Intent intent = new Intent(Team_Opponent.this,TeamList.class);
                startActivity(intent);
                break;


            case R.id.opp:

                intent = new Intent(Team_Opponent.this,OpponentList.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
