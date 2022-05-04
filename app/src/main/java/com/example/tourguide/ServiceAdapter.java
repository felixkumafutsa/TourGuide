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

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList service_id, name, category, service_type, provider_name, phone, email, location;
    public ServiceAdapter(Context context, Activity activity, ArrayList service_id, ArrayList name, ArrayList category,
                          ArrayList service_type, ArrayList provider_name, ArrayList phone, ArrayList email, ArrayList location) {
        this.context = context;
        this.activity = activity;
        this.service_id = service_id;
        this.name = name;
        this.category = category;
        this.service_type = service_type;
        this.provider_name = provider_name;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    @NonNull
    @Override
    public ServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.service_layout, parent, false);
        return new MyViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.service_id_txt.setText(String.valueOf(service_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.category_txt.setText(String.valueOf(category.get(position)));
        holder.service_type_txt.setText(String.valueOf(service_type.get(position)));
        //holder.provider_name_txt.setText(String.valueOf(provider_name.get(position)));
        holder.phone_txt.setText(String.valueOf(phone.get(position)));
        holder.email_txt.setText(String.valueOf(email.get(position)));
        holder.location_txt.setText(String.valueOf(location.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Login.class);
                intent.putExtra("id", String.valueOf(service_id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("category", String.valueOf(category.get(position)));
               // intent.putExtra("provider_name", String.valueOf(provider_name.get(position)));
                intent.putExtra("phone", String.valueOf(phone.get(position)));
                intent.putExtra("email", String.valueOf(email.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return service_id.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView service_id_txt, name_txt, category_txt, service_type_txt, provider_name_txt, phone_txt, email_txt, location_txt;
        LinearLayoutCompat mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            service_id_txt = itemView.findViewById(R.id.serviceId);
            name_txt = itemView.findViewById(R.id.name);
            category_txt = itemView.findViewById(R.id.category);
            service_type_txt = itemView.findViewById(R.id.type);
            provider_name_txt = itemView.findViewById(R.id.provider_name);
            phone_txt = itemView.findViewById(R.id.phone);
            email_txt = itemView.findViewById(R.id.email);
            location_txt = itemView.findViewById(R.id.location);
            mainLayout = itemView.findViewById(R.id.serviceLayout);
            //Animate Recyclerview
            //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }

    }
}
