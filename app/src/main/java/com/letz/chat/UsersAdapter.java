package com.letz.chat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>
{
    List <String> userList;

    public UsersAdapter(List<String> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

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

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textView = itemView.findViewById(R.id.tvUsers_users_card);
            imageView = itemView.findViewById(R.id.ivCircle_users_card);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}

