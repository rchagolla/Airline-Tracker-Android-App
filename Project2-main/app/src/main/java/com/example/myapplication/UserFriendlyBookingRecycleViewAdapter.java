package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.DB.AppDataBase;
import com.example.myapplication.DB.FlightsDAO;

import java.util.List;

public class UserFriendlyBookingRecycleViewAdapter extends RecyclerView.Adapter<UserFriendlyBookingRecycleViewAdapter.MyViewHolder>{
    Context mContext;
    List<Bookings> mBookings;

    public UserFriendlyBookingRecycleViewAdapter(Context context, List<Bookings> bookings) {
        mContext = context;
        mBookings = bookings;
    }

    @NonNull
    @Override
    public UserFriendlyBookingRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.user_friendly_recycleview_row, parent, false);
        return new UserFriendlyBookingRecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserFriendlyBookingRecycleViewAdapter.MyViewHolder holder, int position) {
        FlightsDAO mFlightsDAO = Room.databaseBuilder(mContext, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .createFromAsset("database/AirlineTracker.db")
                .build()
                .FlightsDAO();
        Flights flight = mFlightsDAO.getById(mBookings.get(position).getFlightId());
        holder.mOrigin.setText(flight.getOrigin());
        holder.mDestination.setText(flight.getDestination());
        holder.mCapacity.setText(Integer.toString(flight.getCapacity()));
        holder.mPurchases.setText(Integer.toString(mBookings.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mBookings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mOrigin;
        TextView mDestination;
        TextView mCapacity;
        TextView mPurchases;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mOrigin = itemView.findViewById(R.id.OriginTextView);
            mDestination = itemView.findViewById(R.id.destinationTextView);
            mCapacity = itemView.findViewById(R.id.capacityTextView);
            mPurchases = itemView.findViewById(R.id.purchasesTextView);
        }
    }
}
