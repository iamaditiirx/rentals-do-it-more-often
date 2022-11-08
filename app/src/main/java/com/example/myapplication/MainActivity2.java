package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText name,email,password,aadhar,from,to;
    Button regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password1);
        aadhar=findViewById(R.id.aadhar);
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        regbtn=findViewById(R.id.regbtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              rootNode= FirebaseDatabase.getInstance();
              reference=rootNode.getReference();
              String Name=name.getText().toString();
              String Email=email.getText().toString();
              String Password=password.getText().toString();
              String Aadhar=aadhar.getText().toString();
              String From=from.getText().toString();
              String To=to.getText().toString();

              if(TextUtils.isEmpty(Name)||TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password)||TextUtils.isEmpty(Aadhar)||TextUtils.isEmpty(From)||TextUtils.isEmpty(To)){
                  Toast.makeText(MainActivity2.this,"Input all the fields",Toast.LENGTH_SHORT).show();
              }

              else {
                  UserHelper helper = new UserHelper(Name, Email, Password, Aadhar, From, To);
                  reference.child(Aadhar).setValue(helper);
                  Toast.makeText(MainActivity2.this,"Successfully Registered!",Toast.LENGTH_SHORT).show();
                  Intent i=new Intent(MainActivity2.this, LoginActivity.class);
                  startActivity(i);
              }


            }
        });


    }
}