package com.letz.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyLoginActivity extends AppCompatActivity
{
    ImageView profile;
    private TextView email;
    private TextView password;
    Button signIn;
    Button signUp;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);

        profile = findViewById(R.id.imageViewProfile);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);

        signIn = findViewById(R.id.buttonSignIn);
        signUp = findViewById(R.id.buttonSignUp);

        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });

    }
}