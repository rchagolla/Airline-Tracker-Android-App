package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.DB.AppDataBase;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mLogId;


    private String mUsername;
    private String mUserPassword;
    private int mRewardPoints;

    private int mIsAdmin;

    public User(String username, String userPassword) {
        mUsername = username;
        mUserPassword = userPassword;
        mRewardPoints = 0;
        mIsAdmin = 0;
    }

    public User(String username, String userPassword, int admin) {
        mUsername = username;
        mUserPassword = userPassword;
        mRewardPoints = 0;
        mIsAdmin = admin;
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }

    public int getRewardPoints() {
        return mRewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        mRewardPoints = rewardPoints;
    }

    public int getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        mIsAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "mLogId=" + mLogId +
                ", mUsername='" + mUsername + '\'' +
                ", mUserPassword='" + mUserPassword + '\'' +
                ", mRewardPoints=" + mRewardPoints +
                '}';
    }

}
