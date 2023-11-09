package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DB.AppDataBase;
import com.example.myapplication.DB.FlightsDAO;
import com.example.myapplication.databinding.ActivityChangeFlightBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class changeFlightActivity extends AppCompatActivity {
    private static final String CHANGE_FLIGHT_ACTIVITY_USER = "com.example.myapplication.changeFlightActivityUser";

    ActivityChangeFlightBinding binding;
    EditText mOrigin;
    EditText mDestination;
    EditText mCapacity;
    EditText mFlightID;
    Button mBackButton;
    Button mAddFlightButton;
    Button mDeleteFlightButton;

    FlightsDAO mFlightsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_flight);

        binding = ActivityChangeFlightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mOrigin = binding.editTextOrigin;
        mDestination = binding.editTextDestination;
        mCapacity = binding.editTextCapacity;
        mFlightID = binding.editTextFlightId;
        mBackButton = binding.changeFlightBackButton;
        mAddFlightButton = binding.addFlightButton;
        mDeleteFlightButton = binding.deleteFlightButton;

        mFlightsDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .FlightsDAO();

        int userId = getIntent().getIntExtra(CHANGE_FLIGHT_ACTIVITY_USER, -1);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AdminOptionsActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });

        mAddFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasAddInputs()) {
                    String origin = mOrigin.getText().toString();
                    String destination = mDestination.getText().toString();
                    int capacity = Integer.parseInt(mCapacity.getText().toString());

                    Flights flight = new Flights(origin, destination, capacity);
                    mFlightsDAO.insert(flight);
                    new StyleableToast
                            .Builder(changeFlightActivity.this)
                            .text("FLIGHT ADDED YAY!")
                            .textColor(Color.WHITE)
                            .gravity(Gravity.TOP)
                            .backgroundColor(Color.GREEN)
                            .show();
                }
            }
        });

        mDeleteFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasDeleteInputs()) {
                    int flightId = Integer.parseInt(mFlightID.getText().toString());
                    Flights flight = mFlightsDAO.getById(flightId);
                    if (flight != null) {
                        mFlightsDAO.delete(flight);
                        new StyleableToast
                                .Builder(changeFlightActivity.this)
                                .text("FLIGHT DELETED!")
                                .textColor(Color.WHITE)
                                .gravity(Gravity.TOP)
                                .backgroundColor(Color.GREEN)
                                .show();
                        return;
                    }
                    new StyleableToast
                            .Builder(changeFlightActivity.this)
                            .text("FLIGHT NOT FOUND!")
                            .textColor(Color.WHITE)
                            .gravity(Gravity.TOP)
                            .backgroundColor(Color.RED)
                            .show();
                }
            }
        });
    }

    public boolean hasAddInputs() {
        if (mOrigin.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Where From is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        if (mDestination.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Where To is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        if (mCapacity.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("Capacity is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        return true;
    }

    public boolean hasDeleteInputs() {
        if (mFlightID.getText().toString().isEmpty()) {
            new StyleableToast
                    .Builder(this)
                    .text("FlightID is Empty!")
                    .textColor(Color.WHITE)
                    .gravity(Gravity.TOP)
                    .backgroundColor(Color.RED)
                    .show();
            return false;
        }
        return true;
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, changeFlightActivity.class);
        intent.putExtra(CHANGE_FLIGHT_ACTIVITY_USER, userId);
        return intent;
    }
}