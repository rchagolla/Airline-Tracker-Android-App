package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.DB.AppDataBase;
import com.example.myapplication.DB.UserDAO;
import com.example.myapplication.databinding.ActivityLogBinding;

public class LogActivity extends AppCompatActivity {
    private static final String LOG_ACTIVITY_USER = "com.example.myapplication.LoginActivityUser";

    EditText mUsername;
    EditText mUserPassword;
    TextView mErrMsg;

    Button mSubmit;
    Button mBackButton;

    UserDAO mUserDAO;
    ActivityLogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        binding = ActivityLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUsername = binding.mainUsernameEditText;
        mUserPassword = binding.mainUserPasswordEditText;
        mSubmit = binding.mainSubmitButton;
        mBackButton = binding.goBackButton5;
        mErrMsg = binding.loginErrMsg;

        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .UserDAO();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!logInUser()) {
                    return;
                }
                User user = mUserDAO.getByUsername(mUsername.getText().toString());
                Intent intent = UserLandingActivity.getIntent(getApplicationContext(), user.getLogId());
                startActivity(intent);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private boolean logInUser() {
        String username = mUsername.getText().toString();
        String password = mUserPassword.getText().toString();
        User user = mUserDAO.getByUsername(username);

        if (user == null) {
            mErrMsg.setText(R.string.login_userDNE_msg);
            return false;
        }
        if (!user.getUserPassword().equals(password)) {
            mErrMsg.setText(R.string.incorrect_password_msg);
            return false;
        }
        return true;
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, LogActivity.class);
        intent.putExtra(LOG_ACTIVITY_USER, userId);
        return intent;
    }
}