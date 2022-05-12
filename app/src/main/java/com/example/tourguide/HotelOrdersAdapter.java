package com.example.tourguide;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelOrdersAdapter extends RecyclerView.Adapter<HotelOrdersAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;

    private ArrayList order_id, phone, type, number_occupants, date;

    public HotelOrdersAdapter(Context context, Activity activity, ArrayList order_id, ArrayList phone, ArrayList type, ArrayList number_occupants, ArrayList date) {
        this.context = context;
        this.activity = activity;
        this.order_id = order_id;
        this.phone = phone;
        this.type = type;
        this.number_occupants = number_occupants;
        this.date = date;
    }

    @NonNull
    @Override
    public HotelOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.service_layout, parent, false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull HotelOrdersAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
