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
import com.example.myapplication.DB.BookingsDAO;
import com.example.myapplication.DB.FlightsDAO;
import com.example.myapplication.databinding.ActivityAdminOptionsBinding;
import com.example.myapplication.databinding.ActivityChangeBookingBinding;

import io.github.muddz.styleabletoast.StyleableToast;

public class ChangeBookingActivity extends AppCompatActivity {
    private static final String CHANGE_BOOKING_ACTIVITY_USER = "com.example.myapplication.ChangeBookingActivityUser";
    ActivityChangeBookingBinding binding;
    EditText mBookingId;
    Button mDeleteBooking;
    Button mBackButton;

    BookingsDAO mBookingsDAO;
    FlightsDAO mFlightsDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_booking);

        binding = ActivityChangeBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mBookingId = binding.editTextBookingId;
        mDeleteBooking = binding.deleteBookingButton;
        mBackButton = binding.goBackButton3;

        mBookingsDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .BookingsDAO();

        mFlightsDAO = Room.databaseBuilder(this, AppDataBase.class,AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .FlightsDAO();

        int userId = getIntent().getIntExtra(CHANGE_BOOKING_ACTIVITY_USER, -1);

        mDeleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookings bookings = mBookingsDAO.getById(Integer.parseInt(mBookingId.getText().toString()));
                if (bookings != null) {
                    new StyleableToast
                            .Builder(ChangeBookingActivity.this)
                            .text("Booking Deleted!")
                            .textColor(Color.WHITE)
                            .gravity(Gravity.TOP)
                            .backgroundColor(Color.GREEN)
                            .show();
                    int purchases = bookings.getQuantity();
                    int flightId = bookings.getFlightId();
                    mBookingsDAO.delete(bookings);
                    mFlightsDAO.setPurchases(flightId, mFlightsDAO.getById(flightId).getPurchases()-purchases);
                    return;
                }
                new StyleableToast
                        .Builder(ChangeBookingActivity.this)
                        .text("Booking Not Found!")
                        .textColor(Color.WHITE)
                        .gravity(Gravity.TOP)
                        .backgroundColor(Color.RED)
                        .show();
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AdminOptionsActivity.getIntent(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context, int userId) {
        Intent intent = new Intent(context, ChangeBookingActivity.class);
        intent.putExtra(CHANGE_BOOKING_ACTIVITY_USER, userId);
        return intent;
    }
}