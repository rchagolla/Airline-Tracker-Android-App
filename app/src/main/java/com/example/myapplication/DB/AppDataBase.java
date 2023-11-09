package com.example.myapplication.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.Bookings;
import com.example.myapplication.Flights;
import com.example.myapplication.User;

import java.io.File;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Flights.class, Bookings.class}, version =1)
public abstract class AppDataBase extends RoomDatabase {
    public static final String USER_TABLE = "user";
    public static final String FLIGHTS_TABLE = "flights";
    public static final String BOOKINGS_TABLE = "bookings";
    public static final String DATABASE_NAME = "AirlineTracker";
    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract UserDAO UserDAO();
    public abstract FlightsDAO FlightsDAO();
    public abstract BookingsDAO BookingsDAO();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DATABASE_NAME)
                            .createFromAsset("database/AirlineTracker.db")
                            .build();
                }
            }
        }
        return instance;
    }

}
