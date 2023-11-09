
package com.example.myapplication.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mLogID = :logId")
    List<User> getLogById(int logId);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUsername= :username")
    User getByUsername(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getAllUsers();

    @Query("UPDATE " + AppDataBase.USER_TABLE + " SET mRewardPoints = :rewardPoints " + " WHERE mLogId = :userId")
    void setRewardPoints(int userId, int rewardPoints);
}
