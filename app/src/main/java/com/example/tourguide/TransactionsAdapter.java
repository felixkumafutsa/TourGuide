package com.example.tourguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourguide.ui.gallery.GalleryFragment;

import java.util.ArrayList;

public class TransactionsAdapter extends  RecyclerView.Adapter<TransactionsAdapter.MyViewHolder>{
    private Context context;
    private GalleryFragment galleryFragment;
    private ArrayList payment_id,service_provider, amount, date_paid, payer_email,payer_phone;

    public TransactionsAdapter(Context context, GalleryFragment galleryFragment, ArrayList payment_id, ArrayList service_provider, ArrayList amount, ArrayList date_paid, ArrayList payer_email, ArrayList payer_phone) {
        this.context = context;
        this.galleryFragment = galleryFragment;
        this.payment_id = payment_id;
        this.service_provider = service_provider;
        this.amount = amount;
        this.date_paid = date_paid;
        this.payer_email = payer_email;
        this.payer_phone = payer_phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transactions_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.payment_id_txt.setText(String.valueOf(payment_id.get(position)));
        holder.provider_name_txt.setText(String.valueOf(service_provider.get(position)));
        holder.amount_txt.setText(String.valueOf(amount.get(position)));
        holder.date_paid_txt.setText(String.valueOf(date_paid.get(position)));
        holder.payer_mail_txt.setText(String.valueOf(payer_email.get(position)));
        holder.payer_phone_txt.setText(String.valueOf(payer_phone.get(position)));
        holder.transactionsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BookHotel.class);
                intent.putExtra("payment_id", String.valueOf(payment_id.get(position)));
                intent.putExtra("provider_name", String.valueOf(service_provider.get(position)));
                intent.putExtra("amount", String.valueOf(amount.get(position)));
                intent.putExtra("date_paid", String.valueOf(date_paid.get(position)));
                intent.putExtra("payer_email", String.valueOf(payer_email.get(position)));
                intent.putExtra("payer_phone", String.valueOf(payer_phone.get(position)));

            }
        });
    }

    @Override
    public int getItemCount() {
      return   payment_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView payment_id_txt, provider_name_txt, amount_txt, date_paid_txt, payer_mail_txt, payer_phone_txt;
        LinearLayout transactionsLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            payment_id_txt = itemView.findViewById(R.id.paymentId);
            provider_name_txt = itemView.findViewById(R.id.serviceProvider);
            amount_txt = itemView.findViewById(R.id.amount);
            date_paid_txt = itemView.findViewById(R.id.datePaid);
            payer_mail_txt = itemView.findViewById(R.id.payerEmail);
            payer_phone_txt = itemView.findViewById(R.id.payerPhone);
            transactionsLayout = itemView.findViewById(R.id.transactionsRecycler);
        }
    }
}
