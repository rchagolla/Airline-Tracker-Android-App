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

public class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<FlightsRecyclerViewAdapter.MyViewHolder>{
    Context mContext;
    List<Flights> mFlights;

    public FlightsRecyclerViewAdapter( Context context, List<Flights> flights) {
        mContext = context;
        mFlights = flights;
    }

    @NonNull
    @Override
    public FlightsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.flight_table_recycleview_row, parent, false);
        return new FlightsRecyclerViewAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FlightsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.mFlightId.setText(Integer.toString(mFlights.get(position).getFlightId()));
        holder.mOrigin.setText(mFlights.get(position).getOrigin());
        holder.mDestination.setText(mFlights.get(position).getDestination());
        holder.mCapacity.setText(Integer.toString(mFlights.get(position).getCapacity()));
        holder.mPurchases.setText(Integer.toString(mFlights.get(position).getPurchases()));
    }

    @Override
    public int getItemCount() {
        return mFlights.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mFlightId;
        TextView mOrigin;
        TextView mDestination;
        TextView mCapacity;
        TextView mPurchases;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mFlightId = itemView.findViewById(R.id.flightIdRowTextView);
            mOrigin = itemView.findViewById(R.id.originRowTextView);
            mDestination = itemView.findViewById(R.id.destinationRowTextView);
            mCapacity = itemView.findViewById(R.id.capacityRowTextView);
            mPurchases = itemView.findViewById(R.id.purchasesRowTextView);
        }
    }
}
