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
 * Created by PC on 8/14/2018.
 */

public class TeamAdapter extends ArrayAdapter<Team>
{
    private Context context;

    private LayoutInflater inflater;
    private List<Team> userList;
    ViewHolder viewHolder;


    public TeamAdapter(@NonNull Context context, List<Team> userList) {
        super(context, R.layout.row,userList);
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row, parent, false);


        viewHolder = new ViewHolder();
        viewHolder.name = (TextView) convertView.findViewById(R.id.tvUserName);
        viewHolder.coach = (TextView) convertView.findViewById(R.id.tvUserRole);
        viewHolder.agegroup = (TextView) convertView.findViewById(R.id.tvUserEmail);
        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageProduct);

        convertView.setTag(viewHolder);

        viewHolder.agegroup.setText(userList.get(position).getAgeGroup());
        viewHolder.name.setText((String) userList.get(position).getTeamName());
        viewHolder.coach.setText((String)userList.get(position).getCoach());
        viewHolder.imageView.setImageResource(R.mipmap.taxi);



        return convertView;
    }

    public static class ViewHolder
    {
        TextView name,coach,agegroup;
        ImageView imageView;

    }
}
