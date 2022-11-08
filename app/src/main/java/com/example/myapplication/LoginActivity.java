package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button3=findViewById(R.id.button3);

    }
        private Boolean validateUsername () {
            String val = username.getText().toString();
            if (val.isEmpty()) {
                username.setError("Field cannot be Empty");
                return false;
            } else {
                username.setError(null);

                return true;
            }
        }
        private Boolean validatePassword () {
            String val = password.getText().toString();
            if (val.isEmpty()) {
                password.setError("Field cannot be Empty");
                return false;
            } else {
                password.setError(null);

                return true;
            }
        }
        public void loginUser (View v){
            if (!validateUsername() | !validatePassword())
                return;
            else
                isUser();
        }

        private void isUser () {
            String userEnteredUsername = username.getText().toString();
            String userEnteredPassword = password.getText().toString();

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();



            Query checkUser = reference.orderByChild("aadhar").equalTo(userEnteredUsername);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        username.setError(null);
                        String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                        if (Objects.equals(passwordFromDB, userEnteredPassword)) {
                            password.setError(null);

                            Toast.makeText(LoginActivity.this,"Loginned successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginActivity.this,MainPage.class);
                                    startActivity(i);


                        } else {
                            password.setError("Wrong Password");
                            password.requestFocus();
                        }
                    } else {
                        username.setError("No such user Exists");
                        username.requestFocus();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
