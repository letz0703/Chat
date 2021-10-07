package com.letz.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity
{
    //test

    private TextInputEditText editTextForgot;
    private Button buttonForgot;
    
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        editTextForgot = findViewById(R.id.etEmail_ForgotActivity);
        buttonForgot = findViewById(R.id.btnReset_ForgotActivity);

        auth = FirebaseAuth.getInstance();

        buttonForgot.setOnClickListener(v -> {
            String email = editTextForgot.getText().toString();
            if (! email.equals(""))
            {
                passwordReset(email);
            }
        });
    }
    
    public void passwordReset(String email)
    {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotActivity.this, "password recovery email was sent successfully.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ForgotActivity.this, "There is a problem.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}