package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivitySearchFlightsBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class searchFlightsActivity extends AppCompatActivity {
    private static final String SEARCH_FLIGHTS_ACTIVITY_USER = "com.example.myapplication.SearchFlightsActivityUser";

    ActivitySearchFlightsBinding binding;
    Button mSearchFlights;
    Button mBackButton;
    EditText mWhereTo;
    EditText mWhereFrom;
    EditText mQuantity;
    EditText mDepartureDate;
    EditText mReturnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        binding = ActivitySearchFlightsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mSearchFlights = binding.SearchButton;
        mWhereTo = binding.Arrival;
        mWhereFrom = binding.From;
        mQuantity = binding.QuantityTickets;
        mDepartureDate = binding.DepartureDate;
        mReturnDate = binding.ReturnDate;
        mBackButton = binding.goBackButton8;
        int userId = getIntent().getIntExtra(SEARCH_FLIGHTS_ACTIVITY_USER, -1);

        mSearchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    String whereTo = mWhereTo.getText().toString();
                    String whereFrom = mWhereFrom.getText().toString();
                    int quantity = Integer.parseInt(mQuantity.getText().toString());
                    Intent intent = SearchResultsActivity.getIntent(getApplicationContext(), userId,whereTo, whereFrom, quantity);
                    startActivity(intent);
                }
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = UserLandingActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, searchFlightsActivity.class);
        intent.putExtra(SEARCH_FLIGHTS_ACTIVITY_USER, userId);
        return intent;
    }

    public boolean validateInput() {
        if (mWhereFrom.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Where From is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        if (mWhereTo.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Where To is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        if (mQuantity.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Quantity is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        if (mDepartureDate.getText().toString().isEmpty() || mReturnDate.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Date's are Wrong!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        return true;
    }
}