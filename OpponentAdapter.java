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

public class OpponentAdapter extends ArrayAdapter<Opponent>
{
    private Context context;

    private LayoutInflater inflater;
    private List<Opponent> opponentList;
    ViewHolder viewHolder;

    public OpponentAdapter(@NonNull Context context,  List<Opponent> opponentList) {
        super(context,R.layout.row,opponentList);
        this.context = context;
        this.opponentList = opponentList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.teamName = (TextView) convertView.findViewById(R.id.tvUserName);
        viewHolder.coach = (TextView) convertView.findViewById(R.id.tvUserRole);
        viewHolder.agegroup = (TextView) convertView.findViewById(R.id.tvUserEmail);
        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageProduct);
        convertView.setTag(viewHolder);

        viewHolder.agegroup.setText(opponentList.get(position).getAgegroup());
        viewHolder.coach.setText((String) opponentList.get(position).getCoachName());
        viewHolder.teamName.setText((String)opponentList.get(position).getTeamName());
        viewHolder.imageView.setImageResource(R.mipmap.taxi);


        return convertView;
    }

    public static class ViewHolder
    {
        TextView teamName,coach,agegroup;
        ImageView imageView;

    }
}


