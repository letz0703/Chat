package com.letz.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MyChatActivity extends AppCompatActivity
{
    private ImageView ivBack;
    private TextView tvChat;
    private EditText etChat;
    private FloatingActionButton fabChat;
    private RecyclerView rvChat;

    String userName, fdbName; // Adapter에서 받아 올 녀석들

    FirebaseDatabase database;
    DatabaseReference fdbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat);

        ivBack = findViewById(R.id.ivBack_Chat);
        tvChat = findViewById(R.id.tv_Chat);
        etChat = findViewById(R.id.etml_Chat);
        fabChat = findViewById(R.id.fab_Chat);
        rvChat = findViewById(R.id.rv_Chat);

        database = FirebaseDatabase.getInstance();
        fdbref= database.getReference("");

        userName = getIntent().getStringExtra("userName");
        fdbName = getIntent().getStringExtra("fdbName");
        // Adapter에서 가져온 변수임.
        tvChat.setText(fdbName);
        ivBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //open Main Activity
                Intent igoMainActivity = new Intent(MyChatActivity.this,MainActivity.class);
                startActivity(igoMainActivity);
            }
        });

        fabChat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String message = etChat.getText().toString();
                if (!message.equals(""))
                {
                    sendMessage(message);
                    etChat.setText("");

                }
            }
        });
    }

    private void sendMessage(String message)
    {
        final String key = fdbref.child("Messages").child(userName).child(fdbName).push().getKey();
        final Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("message", message);
        messageMap.put("from", userName);
        fdbref.child("Message").child(userName).child(fdbName).child(key).setValue(messageMap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            fdbref.child("Messages").child(fdbName).child(userName)
                                    .child(key).setValue(messageMap);
                        }

                    }
                });

    }
}