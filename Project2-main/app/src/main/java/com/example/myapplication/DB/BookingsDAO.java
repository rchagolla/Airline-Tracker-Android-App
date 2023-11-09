package com.example.myapplication.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Bookings;
import com.example.myapplication.Flights;
import com.example.myapplication.User;

import java.util.List;

@Dao
public interface BookingsDAO {
    @Insert
    void insert(Bookings... bookings);

    @Update
    void update(Bookings... bookings);

    @Delete
    void delete(Bookings booking);

    @Query("SELECT * FROM " + AppDataBase.BOOKINGS_TABLE + " WHERE mBookingId= :bookingId")
    Bookings getById(int bookingId);

    @Query("SELECT * FROM " + AppDataBase.BOOKINGS_TABLE)
    List<Bookings> getAllBookings();

    @Query("SELECT * FROM " + AppDataBase.BOOKINGS_TABLE + " WHERE mUserId= :userId")
    List<Bookings> getAllBookingsById(int userId);
}
