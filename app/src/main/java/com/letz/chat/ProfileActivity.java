package com.letz.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
{
    private CircleImageView ivCircleProfile;
    private TextView etUserName;
    private Button btnUpdate;

    FirebaseDatabase database;
    DatabaseReference fdbref;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivCircleProfile = findViewById(R.id.ivCircle_ProfileActivity);
        etUserName = findViewById(R.id.etUserName_ProfileActivity);
        btnUpdate = findViewById(R.id.btnUpdateProfile_ProfileActivity);

        database = FirebaseDatabase.getInstance();
        fdbref = database.getReference("");
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        getUserInfo();

        ivCircleProfile.setOnClickListener(v -> {

        });

        btnUpdate.setOnClickListener(v -> {

        });
    }

    public void updateProfile(){
        String userName = etUserName.getText().toString();
        fdbref.child("Users").child(firebaseUser.getUid()).setValue(userName);
    }

    public void getUserInfo() {
        fdbref.child("Users").child(firebaseUser.getUid())
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("userName").getValue().toString();
                        String image = snapshot.child("image").getValue().toString();
                        etUserName.setText(name);

                        //만약 이미지가 없으면 = null 일 경우
                        if (image.equals("null"))
                        {
//                            Default image
                            ivCircleProfile.setImageResource(R.drawable.userIcon);
                        } else {
                            Picasso.get().load(image).into(ivCircleProfile);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


}