package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingsRecyclerViewAdapter extends RecyclerView.Adapter<BookingsRecyclerViewAdapter.MyViewHolder>{
    Context mContext;
    List<Bookings> mBookings;

    public BookingsRecyclerViewAdapter(Context context, List<Bookings> bookings) {
        mContext = context;
        mBookings = bookings;
    }

    @NonNull
    @Override
    public BookingsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.booking_table_recyclerview_row, parent, false);
        return new BookingsRecyclerViewAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookingsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.mBookingId.setText(Integer.toString(mBookings.get(position).getBookingId()));
        holder.mUserId.setText(Integer.toString(mBookings.get(position).getUserId()));
        holder.mFlightId.setText(Integer.toString(mBookings.get(position).getFlightId()));
        holder.mQuantity.setText(Integer.toString(mBookings.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return mBookings.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mFlightId;
        TextView mBookingId;
        TextView mUserId;
        TextView mQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mBookingId = itemView.findViewById(R.id.bookingIdRecyclertextView);
            mUserId = itemView.findViewById(R.id.userIdBookingRecyclertextView);
            mFlightId = itemView.findViewById(R.id.flightIdBookingRecyclertextView);
            mQuantity = itemView.findViewById(R.id.quantityBookingRecyclertextView);
        }
    }
}
