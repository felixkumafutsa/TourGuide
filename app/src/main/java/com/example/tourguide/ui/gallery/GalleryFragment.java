package com.example.tourguide.ui.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourguide.AddAdmin;
import com.example.tourguide.AddService;
import com.example.tourguide.DatabaseAccess;
import com.example.tourguide.R;
import com.example.tourguide.ServiceAdapter;
import com.example.tourguide.ServiceProvider;
import com.example.tourguide.Services;
import com.example.tourguide.ServicesMapping;
import com.example.tourguide.TransactionsAdapter;
import com.example.tourguide.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    ArrayList payment_id;
    ArrayList service_provider;
    ArrayList amount;
    ArrayList date_paid;
    ArrayList payer_email;
    ArrayList payer_phone;
    Button serviceProviders, addService, logout;
    RecyclerView transactions;

    TransactionsAdapter transactionsAdapter;
    DatabaseAccess myDB;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        myDB = new DatabaseAccess(getContext());
        payment_id = new ArrayList<>();
        service_provider = new ArrayList<>();
        amount = new ArrayList<>();
        date_paid = new ArrayList<>();
        payer_email = new ArrayList<>();
        payer_phone = new ArrayList<>();
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {


        storeDataInArrays();
        transactions = view.findViewById(R.id.transactionsRecycler);
        transactionsAdapter = new TransactionsAdapter(getContext(),this, payment_id, service_provider, amount, date_paid, payer_email, payer_phone);
        transactions.setAdapter(transactionsAdapter);
       transactions.setLayoutManager(new LinearLayoutManager(getContext()));


        serviceProviders = view.findViewById(R.id.serviceProviders);
        addService = view.findViewById(R.id.addService);
        logout = view.findViewById(R.id.logout);
        ImageButton services = (ImageButton) view.findViewById(R.id.viewHotels);
        ImageButton maps = (ImageButton) view.findViewById(R.id.viewMap);
        ImageButton addAdmin= (ImageButton) view.findViewById(R.id.addAdmin);




    }

    private void storeDataInArrays() {
        DatabaseAccess myDB = new DatabaseAccess(getContext());
        Cursor cursor = myDB.readAllTransactions();
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                payment_id.add(cursor.getString(0));
                service_provider.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                date_paid.add(cursor.getString(3));
                payer_email.add(cursor.getString(4));
                payer_phone.add(cursor.getString(5));
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}