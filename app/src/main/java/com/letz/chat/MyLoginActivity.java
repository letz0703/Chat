package com.letz.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MyLoginActivity extends AppCompatActivity
{
    ImageView profile;
    private TextView email,password;
    private Button buttonSignIn, buttonSignUp;
    private TextView textViewForgot;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);

        profile = findViewById(R.id.ivProfile_Login);
        email = findViewById(R.id.etEmail_Login);
        password = findViewById(R.id.etPassword_Login);

        buttonSignIn = findViewById(R.id.btnSignin_Login);
        buttonSignUp = findViewById(R.id.btnSignup_Login);
        
        textViewForgot = findViewById(R.id.btnForgot_Login);

        auth = FirebaseAuth.getInstance();

        buttonSignIn.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            if (!email.equals("")&& !password.equals(""))
            {
                signin(userEmail, userPassword);
            }
            else
            {
                Toast.makeText(MyLoginActivity.this, "Please enter and email and password.", Toast.LENGTH_SHORT).show();
            }

        });

        buttonSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyLoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        
        textViewForgot.setOnClickListener(v -> {
            Intent i = new Intent(MyLoginActivity.this,ForgotActivity.class);
            startActivity(i);
        });
       

    }

    public void signin(String email, String password)
    {
        auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent i = new Intent(MyLoginActivity.this,MainActivity.class);
                    Toast.makeText(MyLoginActivity.this, "Sign in is successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MyLoginActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}