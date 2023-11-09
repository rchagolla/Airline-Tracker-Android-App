package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserFriendlyRecyclerViewAdapter extends RecyclerView.Adapter<UserFriendlyRecyclerViewAdapter.MyViewHolder>{
    private final RecyclerViewInterface mRecyclerViewInterface;
    Context mContext;
    List<Flights> mFlights;

    public UserFriendlyRecyclerViewAdapter( Context context, List<Flights> flights, RecyclerViewInterface recyclerViewInterface) {
        mContext = context;
        mFlights = flights;
        mRecyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public UserFriendlyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.user_friendly_recycleview_row, parent, false);
        return new UserFriendlyRecyclerViewAdapter.MyViewHolder(view, mRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull UserFriendlyRecyclerViewAdapter.MyViewHolder holder, int position) {
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
        TextView mOrigin;
        TextView mDestination;
        TextView mCapacity;
        TextView mPurchases;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            mOrigin = itemView.findViewById(R.id.OriginTextView);
            mDestination = itemView.findViewById(R.id.destinationTextView);
            mCapacity = itemView.findViewById(R.id.capacityTextView);
            mPurchases = itemView.findViewById(R.id.purchasesTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
