package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.MyViewHolder>{

     Context context;

    ArrayList<MainModel> list;


    public AndroidAdapter(Context context, ArrayList<MainModel> list) {
        this.context = context;
        this.list = list;
      //  MainModel e = new MainModel("amiya","afaf0","aafafaf");
      //  list.add(e);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.one_view,parent,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainModel user = list.get(position);
        holder.Name.setText(user.getName());
        holder.Email.setText(user.getEmail());
        holder.Aadhar.setText(user.getAadhar());
    }





    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name,Email,Aadhar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.Name);
            Email = itemView.findViewById(R.id.Email);
           Aadhar = itemView.findViewById(R.id.Aadhar);

        }
    }

}
