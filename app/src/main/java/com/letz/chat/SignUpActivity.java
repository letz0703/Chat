package com.letz.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity
{
    private CircleImageView imageViewCircle;
    private TextInputEditText email, password, username;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imageViewCircle = findViewById(R.id.ivCircle);
        email = findViewById(R.id.etEmailSignUp);
        password = findViewById(R.id.etPasswordSignUp);
        username = findViewById(R.id.etUserNameSignUp);
        register = findViewById(R.id.btnRegister);
    }
}