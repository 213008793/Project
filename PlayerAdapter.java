package com.example.pc.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 8/12/2018.
 */

public class PlayerAdapter extends ArrayAdapter<Player>
{

    private Context context;

    private LayoutInflater inflater;
    private List<Player> playerList;
    ViewHolder viewHolder;

    public PlayerAdapter(@NonNull Context context, List<Player> playerList) {
        super(context, R.layout.row,playerList);
        this.context = context;
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.name = (TextView) convertView.findViewById(R.id.tvUserName);
        viewHolder.role = (TextView) convertView.findViewById(R.id.tvUserRole);
        viewHolder.email = (TextView) convertView.findViewById(R.id.tvUserEmail);
        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageProduct);

        convertView.setTag(viewHolder);

        viewHolder.email.setText(playerList.get(position).getPlayerName());
        viewHolder.name.setText((String) playerList.get(position).getTeamName());
        viewHolder.role.setText((String)playerList.get(position).getAllegies());
        viewHolder.imageView.setImageResource(R.mipmap.taxi);


        return convertView;
    }

    public static class ViewHolder
    {
        TextView name,email,role;
        ImageView imageView;

    }
}
