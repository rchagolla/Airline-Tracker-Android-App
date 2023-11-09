package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    List<User> mUsers;

    public UserRecyclerViewAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public UserRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.user_table_recyclerview_row, parent, false);
        return new UserRecyclerViewAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.mUserId.setText(Integer.toString(mUsers.get(position).getLogId()));
        holder.mUsername.setText(mUsers.get(position).getUsername());
        holder.mUserPassword.setText(mUsers.get(position).getUserPassword());
        holder.mRewardPoints.setText(Integer.toString(mUsers.get(position).getRewardPoints()));
        if (mUsers.get(position).getIsAdmin() == 1) {
            holder.mIsAdmin.setText("True");
        } else {
            holder.mIsAdmin.setText("False");
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mUserId;
        TextView mUsername;
        TextView mUserPassword;
        TextView mRewardPoints;
        TextView mIsAdmin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mUserId = itemView.findViewById(R.id.userIdRowTextView);
            mUsername = itemView.findViewById(R.id.usernameRowTextView);
            mUserPassword = itemView.findViewById(R.id.userPasswordRowTextView);
            mRewardPoints = itemView.findViewById(R.id.userRewardPointsRowTextView);
            mIsAdmin = itemView.findViewById(R.id.userIsAdminRowTextView);
        }
    }
}
