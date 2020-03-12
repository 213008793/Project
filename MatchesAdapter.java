package com.example.pc.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 9/6/2018.
 */

public class MatchesAdapter extends ArrayAdapter<Player>
{

    private Context context;

    private LayoutInflater inflater;
    private List<Player> playerList;
    ArrayAdapter adapter;
    final List<Player> player_name = new ArrayList<Player>();
    MatchesAdapter.ViewHolder viewHolder;


    public MatchesAdapter(@NonNull Context context,  List<Player> playerList)
    {
        super(context, R.layout.match_lineup);
        this.context = context;
        this.playerList = playerList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.match_lineup, parent, false);

        viewHolder = new MatchesAdapter.ViewHolder();

        viewHolder.Position = (Spinner)convertView.findViewById(R.id.spPositions);
        viewHolder.players = (Spinner)convertView.findViewById(R.id.spPlayer);
        viewHolder.Goals = (TextView)convertView.findViewById(R.id.tvGoals);
        viewHolder.GoalsScored =(TextView)convertView.findViewById(R.id.tvGoalsScored);
        viewHolder.ratingBar =(RatingBar)convertView.findViewById(R.id.ratingBar);
        convertView.setTag(viewHolder);


        viewHolder.Position.setAdapter(adapter);
        viewHolder.Position.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

            }
        });

         viewHolder.players.setAdapter(adapter);

         viewHolder.players.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
             {

             }
         });


// Player player = player_name.get()
         viewHolder.Goals.setText("Goals");
         viewHolder.ratingBar.getRating();
         viewHolder.GoalsScored.setText("");



        return convertView;
    }



    public static class ViewHolder
    {
        Spinner Position,players;
        RatingBar ratingBar;
        TextView Goals,GoalsScored;
    }
}
