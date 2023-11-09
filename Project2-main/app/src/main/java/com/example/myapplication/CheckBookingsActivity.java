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

import com.example.myapplication.DB.AppDataBase;
import com.example.myapplication.DB.BookingsDAO;
import com.example.myapplication.databinding.ActivityCheckBookingsBinding;

import java.util.List;

public class CheckBookingsActivity extends AppCompatActivity {

    RecyclerView mCheckBookings;
    ActivityCheckBookingsBinding binding;



    private static final String USER_CHECK_BOOKINGS = "com.example.myapplication.UserCheckBookings";

    BookingsDAO mBookingsDAO;
    Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bookings);

        binding = ActivityCheckBookingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mBackButton = binding.goBackButton7;
        int userId = getIntent().getIntExtra(USER_CHECK_BOOKINGS, -1);

        mBookingsDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .BookingsDAO();

        List<Bookings> bookings = mBookingsDAO.getAllBookingsById(userId);
        mCheckBookings = findViewById(R.id.CheckBookingsRecyclerView);
        mCheckBookings.setAdapter(new UserFriendlyBookingRecycleViewAdapter(this, bookings));
        mCheckBookings.setLayoutManager(new LinearLayoutManager(this));

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = UserLandingActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, CheckBookingsActivity.class);
        intent.putExtra(USER_CHECK_BOOKINGS, userId);
        return intent;
    }
}