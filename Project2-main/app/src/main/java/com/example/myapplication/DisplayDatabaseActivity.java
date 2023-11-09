package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.DB.AppDataBase;
import com.example.myapplication.DB.BookingsDAO;
import com.example.myapplication.DB.FlightsDAO;
import com.example.myapplication.DB.UserDAO;
import com.example.myapplication.databinding.ActivityDisplayDatabaseBinding;

import java.util.ArrayList;
import java.util.List;

public class DisplayDatabaseActivity extends AppCompatActivity {
    private static final String DISPLAY_DATABASE_ACTIVITY_USER = "com.example.myapplication.DisplayDatabaseActivityUser";
    ActivityDisplayDatabaseBinding binding;
    RecyclerView mUserRecyclerView;
    RecyclerView mFlightsRecyclerView;
    RecyclerView mBookingsRecyclerView;
    Button mBackButton;
    UserDAO mUserDAO;
    FlightsDAO mFlightsDAO;
    BookingsDAO mBookingsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_database);

        binding = ActivityDisplayDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .UserDAO();
        mFlightsDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .FlightsDAO();
        mBookingsDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .BookingsDAO();

        int userId = getIntent().getIntExtra(DISPLAY_DATABASE_ACTIVITY_USER, -1);
        mBackButton = binding.goBackButton4;

        //setting up user recycle view
        List<User> users = mUserDAO.getAllUsers();
        mUserRecyclerView = findViewById(R.id.userTableRecyclerView);
        mUserRecyclerView.setAdapter(new UserRecyclerViewAdapter(this, users));
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting up flights recycle view
       List<Flights> flights = mFlightsDAO.getAllFlights();
        mFlightsRecyclerView = findViewById(R.id.flightTableRecyclerView);
        mFlightsRecyclerView.setAdapter(new FlightsRecyclerViewAdapter(this, flights));
        mFlightsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting up bookings recycle view
        List<Bookings> bookings = mBookingsDAO.getAllBookings();
        mBookingsRecyclerView = findViewById(R.id.bookingTableRecyclerView);
        mBookingsRecyclerView.setAdapter(new BookingsRecyclerViewAdapter(this, bookings));
        mBookingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AdminOptionsActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, DisplayDatabaseActivity.class);
        intent.putExtra(DISPLAY_DATABASE_ACTIVITY_USER, userId);
        return intent;
    }
}