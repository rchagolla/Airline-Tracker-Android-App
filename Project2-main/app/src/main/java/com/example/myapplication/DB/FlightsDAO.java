package com.example.myapplication.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Flights;
import com.example.myapplication.User;

import java.util.List;

@Dao
public interface FlightsDAO {
    @Insert
    void insert(Flights... flights);

    @Update
    void update(Flights... flights);

    @Delete
    void delete(Flights flight);

    @Query("SELECT * FROM " + AppDataBase.FLIGHTS_TABLE + " WHERE mFlightId= :flightId")
    Flights getById(int flightId);

    @Query("SELECT * FROM " + AppDataBase.FLIGHTS_TABLE)
    List<Flights> getAllFlights();

    @Query("SELECT * FROM " + AppDataBase.FLIGHTS_TABLE + " WHERE mOrigin = :origin AND mDestination = :destination")
    List<Flights> getByOriginAndDestination(String origin, String destination);

    @Query("UPDATE " + AppDataBase.FLIGHTS_TABLE + " SET mPurchases = :purchases " + " WHERE mFlightId = :flightId")
    void setPurchases(int flightId, int purchases);
}
