package com.letz.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
{
    private CircleImageView circleImage;
    private TextView etUserName;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        circleImage = findViewById(R.id.ivCircle_ProfileActivity);
        etUserName = findViewById(R.id.etUserName_ProfileActivity);
        btnUpdate = findViewById(R.id.btnUpdateProfile_ProfileActivity);

        btnUpdate.setOnClickListener(v -> {

        });
    }



}