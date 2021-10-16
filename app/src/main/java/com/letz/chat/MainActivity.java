package com.letz.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    FirebaseAuth auth;
    RecyclerView rv;
// FirebaseAuth to Logout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater manuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.chat_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // item id가 버튼 id와 같을 경우 R.id.action
        if (item.getItemId() == R.id.actionProfile_chat_menu) {
            Intent igoProfileActivity = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(igoProfileActivity);
        }
        if (item.getItemId() == R.id.actionLogout_chat_menu) {
            auth.signOut();
            startActivity(new Intent(this, MyLoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}