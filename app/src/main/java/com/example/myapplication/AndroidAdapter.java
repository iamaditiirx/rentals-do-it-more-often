package com.example.myapplication;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.jar.Attributes;

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
                    public void onClick(View view) {

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

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Name,Email,Aadhar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            Name = itemView.findViewById(R.id.Name);
            Email = itemView.findViewById(R.id.Email);
            Aadhar = itemView.findViewById(R.id.Aadhar);
        }
        @Override
        public void onClick(View view) {
            MainPage mp=new MainPage();
            String addresses=mp.email;
            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setData(Uri.parse("mailto:"));
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
               context.startActivity(intent);

            }
        }


    }

}
