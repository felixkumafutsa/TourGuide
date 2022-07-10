package com.example.tourguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransportOrdersAdapter extends RecyclerView.Adapter<TransportOrdersAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;

    private ArrayList order_id, phone, type, number_occupants, date;

    public TransportOrdersAdapter(Context context, Activity activity, ArrayList order_id, ArrayList phone, ArrayList type, ArrayList number_occupants, ArrayList date) {
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
    public TransportOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_layout, parent, false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull TransportOrdersAdapter.MyViewHolder holder, int position) {
        holder.order_id_txt.setText(String.valueOf(order_id.get(position)));
        holder.phone_txt.setText(String.valueOf(phone.get(position)));
        holder.type_txt.setText(String.valueOf(type.get(position)));
        holder.number_txt.setText(String.valueOf(number_occupants.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.hotelOrdersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookHotel.class);
                intent.putExtra("id", String.valueOf(order_id.get(position)));;
                intent.putExtra("phone", String.valueOf(phone.get(position)));
                intent.putExtra("type", String.valueOf(type.get(position)));
                intent.putExtra("number_of_occupants", String.valueOf(number_occupants.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return order_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_id_txt, phone_txt, type_txt, number_txt, date_txt;
        LinearLayoutCompat hotelOrdersLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            order_id_txt = itemView.findViewById(R.id.serviceId);
            phone_txt = itemView.findViewById(R.id.name);
            type_txt = itemView.findViewById(R.id.category);
            number_txt = itemView.findViewById(R.id.type);
            date_txt = itemView.findViewById(R.id.provider_name);
            hotelOrdersLayout = itemView.findViewById(R.id.hotel_order_recycler);
            //Animate Recyclerview
            //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }
    }
}
