package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.DB.AppDataBase;

@Entity(tableName = AppDataBase.FLIGHTS_TABLE)
public class Flights {
    @PrimaryKey(autoGenerate = true)
    private int mFlightId;

    private String mOrigin;
    private String mDestination;
    private int mCapacity;
    private int mPurchases;

    public Flights(String origin, String destination, int capacity) {
        mOrigin = origin;
        mDestination = destination;
        mCapacity = capacity;
        mPurchases = 0;
    }

    public int getFlightId() {
        return mFlightId;
    }

    public void setFlightId(int flightId) {
        mFlightId = flightId;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void setOrigin(String origin) {
        mOrigin = origin;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String destination) {
        mDestination = destination;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public void setCapacity(int capacity) {
        mCapacity = capacity;
    }

    public int getPurchases() {
        return mPurchases;
    }

    public void setPurchases(int purchases) {
        mPurchases = purchases;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "mFlightId=" + mFlightId +
                ", mOrigin='" + mOrigin + '\'' +
                ", mDestination='" + mDestination + '\'' +
                ", mCapacity=" + mCapacity +
                ", mPurchases=" + mPurchases +
                '}';
    }
}
