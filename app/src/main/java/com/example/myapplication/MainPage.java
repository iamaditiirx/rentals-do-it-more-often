package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainPage extends AppCompatActivity {
    RecyclerView rv;
    AndroidAdapter myAdapter;
    ArrayList<MainModel> list = new ArrayList<>();
    EditText from;
    EditText to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            rv = findViewById(R.id.rv);

            from = findViewById(R.id.from);
            to = findViewById(R.id.to);
            Button b = findViewById(R.id.abcde);

            myAdapter=new AndroidAdapter(this,list);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(myAdapter);


            b.setOnClickListener(v -> {
                list.clear();
                String userenteredfrom = Objects.requireNonNull(from. getText().toString());
                String userenteredto = Objects.requireNonNull(to.getText().toString());
//                    System.out.println(userenteredfrom);
//                    System.out.println(userenteredto);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot key : snapshot.getChildren()){
                           // System.out.println();
                           // System.out.println(key.getValue());
                            System.out.println();
                            if (snapshot.exists()) {
                                from.setError(null);
                                String toFromDB = snapshot.child(key.getKey()).child("to").getValue().toString();
                                if (Objects.equals(toFromDB, userenteredto)) {
                                    to.setError(null);
                                    String name="";
                                    String email="";
                                    String aadhar="";

                                    for (DataSnapshot dataSnapshot : key.getChildren()) {

                                        if(Objects.equals(dataSnapshot.getKey(), "name") ){
                                            name=dataSnapshot.getValue().toString();
                                        }
                                        if(Objects.equals(dataSnapshot.getKey(), "email")){
                                            email=dataSnapshot.getValue().toString();
                                        } if(Objects.equals(dataSnapshot.getKey(), "aadhar") ){
                                            aadhar=dataSnapshot.getValue().toString();
                                        }



                                    }
                                    MainModel e = new MainModel(name,email,aadhar);
                                    list.add(e);
                                }else{
                                    to.setError("change destination");
                                    to.requestFocus();
                                }
                            } else {
                                from.setError("change pickup point");
                                from.requestFocus();
                            }
                            myAdapter.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            });






    }
}
