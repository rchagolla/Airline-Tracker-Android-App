package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.DB.AppDataBase;
import com.example.myapplication.DB.UserDAO;
import com.example.myapplication.databinding.ActivityUserLandingBinding;

import java.util.List;

public class UserLandingActivity extends AppCompatActivity {
    private static final String USER_LANDING_ACTIVITY_USER = "com.example.myapplication.UserLandingActivityUser";

    TextView mWelcomeDisplay;
    ActivityUserLandingBinding binding;
    Button mAdminOptionsButton;
    Button mAccountDetailsButton;
    Button mSearchFlights;
    Button mCheckBookings;
    Button mLogout;
    UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);

        binding = ActivityUserLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mWelcomeDisplay = binding.landingWelcomeMsg;
        mAdminOptionsButton = binding.adminOptionsButton;
        mAccountDetailsButton = binding.accountDetailsButton;
        mSearchFlights = binding.searchFlightsButton;
        mCheckBookings = binding.checkBookingsButton;
        mLogout = binding.logoutButton;

        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .UserDAO();

        int userId = getIntent().getIntExtra(USER_LANDING_ACTIVITY_USER, -1);
        List<User> user = mUserDAO.getLogById(userId);
        mWelcomeDisplay.setText(getString(R.string.landingPagePartial) + " " + user.get(0).getUsername());

        if (user.get(0).getIsAdmin() == 1) {
            mAdminOptionsButton.setVisibility(View.VISIBLE);
        }

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mAdminOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AdminOptionsActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });

        mAccountDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AccountDetailsActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });

        mCheckBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CheckBookingsActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });

        mSearchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = searchFlightsActivity.getIntent(getApplicationContext(),userId);
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, UserLandingActivity.class);
        intent.putExtra(USER_LANDING_ACTIVITY_USER, userId);
        return intent;
    }
}