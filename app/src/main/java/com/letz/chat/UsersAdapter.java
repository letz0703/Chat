package com.letz.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>
{
    List<String> userList;
    String userName;
    Context mContext;

    FirebaseDatabase database;
    DatabaseReference fdbref;

    public UsersAdapter(List<String> userList, String userName, Context mContext) {
        this.userList = userList;
        this.userName = userName;
        this.mContext = mContext;

        database = FirebaseDatabase.getInstance();
        fdbref = database.getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        fdbref.child(userList.get(position)).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fdbName = snapshot.child("userName").getValue().toString();
                String fdbImage = snapshot.child("image").getValue().toString();
                // 가져온 이미지 보여주기
                holder.textView.setText(fdbName);
                //image에 있어
                // 없으면 디폴트 이미지 준다
                if (fdbImage.equals("null")) {
                    holder.imageView.setImageResource(R.drawable.user_icon);
                } else {
                    Picasso.get().load(fdbImage).into(holder.imageView);
                }

                // onClick to CardView
                holder.cardView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent igoMyChatActivity = new Intent(mContext,MyChatActivity.class);
                        igoMyChatActivity.putExtra("userName",userName);
                        igoMyChatActivity.putExtra("fdbName",fdbName);
                        mContext.startActivity(igoMyChatActivity);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;
        private ImageView imageView;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvUsers_users_card);
            imageView = itemView.findViewById(R.id.ivCircle_users_card);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

