package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.DB.AppDataBase;

@Entity(tableName = AppDataBase.BOOKINGS_TABLE)
public class Bookings {
    @PrimaryKey(autoGenerate = true)
    private int mBookingId;

    private int mUserId;
    private int mFlightId;
    private int mQuantity;

    public Bookings(int userId, int flightId, int quantity) {
        mUserId = userId;
        mFlightId = flightId;
        mQuantity = quantity;
    }

    public int getBookingId() {
        return mBookingId;
    }

    public void setBookingId(int bookingId) {
        mBookingId = bookingId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getFlightId() {
        return mFlightId;
    }

    public void setFlightId(int flightId) {
        mFlightId = flightId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "mBookingId=" + mBookingId +
                ", mUserId=" + mUserId +
                ", mFlightId=" + mFlightId +
                ", mQuantity=" + mQuantity +
                '}';
    }
}
