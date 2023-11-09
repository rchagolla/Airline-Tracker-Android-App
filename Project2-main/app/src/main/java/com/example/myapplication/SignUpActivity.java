package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
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
import com.example.myapplication.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mUserPassword;
    EditText mUserPasswordVerify;

    TextView mErrMsgView;

    Button mSubmit;
    Button mBackButton;
    UserDAO mUserDAO;
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUsername = binding.mainUsernameEditText;
        mUserPassword = binding.mainUserPasswordEditText;
        mUserPasswordVerify = binding.mainUserPasswordVerifyEditText;
        mSubmit = binding.mainSignUpButton;
        mBackButton = binding.goBackButton6;
        mErrMsgView = binding.signUpErrMsg;

        mUserDAO= Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .UserDAO();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (mUserDAO.getByUsername(mUsername.getText().toString()) != null) {
                    mErrMsgView.setText(R.string.username_taken_msg);
                    return;
                }
                if (!mUserPassword.getText().toString().equals(mUserPasswordVerify.getText().toString())) {
                    mErrMsgView.setText(R.string.password_mismatch_message);
                    return;
                }
                submitUser();
                Intent intent = MainActivity.getIntent(getApplicationContext());
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

    private void submitUser(){
        String username = mUsername.getText().toString();
        String password = mUserPassword.getText().toString();

        User log = new User(username,password);
        mUserDAO.insert(log);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        return intent;
    }
}