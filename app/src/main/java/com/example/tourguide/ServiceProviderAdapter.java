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

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList id, serviceName, category, type, providerName, providerPhone,  providerEmail, location;

    public ServiceProviderAdapter(Context context, Activity activity, ArrayList id, ArrayList serviceName, ArrayList category, ArrayList type, ArrayList providerName, ArrayList providerPhone, ArrayList providerEmail, ArrayList location) {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.serviceName = serviceName;
        this.category = category;
        this.type = type;
        this.providerName = providerName;
        this.providerPhone = providerPhone;
        this.providerEmail = providerEmail;
        this.location = location;
    }


    @NonNull
    @Override
    public ServiceProviderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.service_provider_layout, parent, false);
        return new MyViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.serviceName_txt.setText(String.valueOf(serviceName.get(position)));
        holder.providerName_txt.setText(String.valueOf(providerName.get(position)));
        holder.providerEmail_txt.setText(String.valueOf(providerEmail.get(position)));
        holder.providerPhone_txt.setText(String.valueOf(providerPhone.get(position)));
        holder.location_txt.setText(String.valueOf(location.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Services.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(serviceName.get(position)));
                intent.putExtra("phone", String.valueOf(providerPhone.get(position)));
                intent.putExtra("email", String.valueOf(providerEmail.get(position)));
                intent.putExtra("location", String.valueOf(location.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt,serviceName_txt,  providerName_txt, providerPhone_txt,  providerEmail_txt, location_txt;
        LinearLayoutCompat mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id);
            providerName_txt = itemView.findViewById(R.id.name);
            providerEmail_txt = itemView.findViewById(R.id.email);
            providerPhone_txt = itemView.findViewById(R.id.phone);
            serviceName_txt = itemView.findViewById(R.id.serviceNamee);
            location_txt = itemView.findViewById(R.id.location);
            mainLayout = itemView.findViewById(R.id.service_provider_layout);
            //Animate Recyclerview
            //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }

    }
}
