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
import com.example.myapplication.databinding.ActivityAccountDetailsBinding;

import java.util.List;

public class AccountDetailsActivity extends AppCompatActivity {

    private static final String ACCOUNT_DETAILS_ACTIVITY = "com.example.myapplication.AccountDetailsActivity";

    ActivityAccountDetailsBinding binding;
    TextView mDisplayUser;
    TextView mRewardPoints;
    Button mBackButton;
    UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        binding = ActivityAccountDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRewardPoints = binding.rewardPtsTextView;

        mDisplayUser = binding.usernameTextView;
        mBackButton = binding.goBackButton9;

        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .UserDAO();
        int userId = getIntent().getIntExtra(ACCOUNT_DETAILS_ACTIVITY, -1);
        List<User> user = mUserDAO.getLogById(userId);
        mDisplayUser.setText(user.get(0).getUsername());
        mRewardPoints.setText(Integer.toString(user.get(0).getRewardPoints()));

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = UserLandingActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, AccountDetailsActivity.class);
        intent.putExtra(ACCOUNT_DETAILS_ACTIVITY, userId);
        return intent;
    }
}