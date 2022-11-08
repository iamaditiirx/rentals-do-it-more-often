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
    TextInputEditText from;
    TextInputEditText to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            rv = findViewById(R.id.rv);

            from = findViewById(R.id.from);
            to = findViewById(R.id.to);
            String userenteredfrom = Objects.requireNonNull(from. getText().toString());
            String userenteredto = Objects.requireNonNull(to.getText().toString());
            myAdapter=new AndroidAdapter(this,list);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(myAdapter);




        Query checkUser = reference.orderByChild("aadhar").equalTo(userenteredfrom);

        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    from.setError(null);
                    String toFromDB = snapshot.child("to").getValue(String.class);
                    if (Objects.equals(toFromDB, userenteredto)) {
                        to.setError(null);

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            MainModel user = dataSnapshot.getValue(MainModel.class);
                            list.add(user);


                        }
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




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
