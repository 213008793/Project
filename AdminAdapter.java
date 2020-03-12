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

import com.backendless.BackendlessUser;

import java.util.List;

/**
 * Created by PC on 7/30/2018.
 */

public class AdminAdapter extends ArrayAdapter<BackendlessUser>
{
    private Context context;

    private LayoutInflater inflater;
    private List<BackendlessUser> userList;
    ViewHolder viewHolder;

    public AdminAdapter(@NonNull Context context, List<BackendlessUser> userList) {
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
        viewHolder.role = (TextView) convertView.findViewById(R.id.tvUserRole);
        viewHolder.email = (TextView) convertView.findViewById(R.id.tvUserEmail);
        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageProduct);

        convertView.setTag(viewHolder);

        viewHolder.email.setText(userList.get(position).getEmail());
        viewHolder.name.setText((String) userList.get(position).getProperty("name"));
        viewHolder.role.setText((String)userList.get(position).getProperty("role"));
        viewHolder.imageView.setImageResource(R.mipmap.taxi);


        return convertView;



    }

    public static class ViewHolder
    {
        TextView name,email,role;
        ImageView imageView;

    }
}
