package com.letz.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class ForgotActivity extends AppCompatActivity
{
    //test

    private TextInputEditText editTextForgot;
    private Button buttonForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        editTextForgot = findViewById(R.id.etEmail_ForgotActivity);
        buttonForgot = findViewById(R.id.btnReset_ForgotActivity);


    }
}