package com.letz.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyChatActivity extends AppCompatActivity
{
    private ImageView btnBack;
    private TextView tvChat;
    private EditText etChat;
    private FloatingActionButton fabChat;
    private RecyclerView rvChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat);

        btnBack = findViewById(R.id.ivBack_Chat);
        tvChat = findViewById(R.id.tv_Chat);
        etChat = findViewById(R.id.etml_Chat);
        fabChat = findViewById(R.id.fab_Chat);
        rvChat = findViewById(R.id.rv_Chat);
    }
}