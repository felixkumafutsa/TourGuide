package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TransportOrders extends AppCompatActivity {
    ImageButton transportServices, lakeShoreFacilities, events;
    RecyclerView recyclerView;
    DatabaseAccess myDB;
    ArrayList<String> order_id,phone, type, number_of_occupants, date;
    HotelOrdersAdapter hotelOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Toolbar mtoobar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoobar);
        transportServices = findViewById(R.id.transportServices);
        lakeShoreFacilities = findViewById(R.id.lakeshoreFacilities);
        events = findViewById(R.id.events);
        recyclerView = findViewById(R.id.hotel_order_recycler);
        myDB = new DatabaseAccess(TransportOrders.this);
        order_id = new ArrayList<>();
        phone = new ArrayList<>();
        type = new ArrayList<>();
        number_of_occupants = new ArrayList<>();
        date = new ArrayList<>();

        storeDataInArrays();

        hotelOrdersAdapter = new HotelOrdersAdapter(TransportOrders.this,this, order_id,phone, type, number_of_occupants, date);
        recyclerView.setAdapter(hotelOrdersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TransportOrders.this));

        transportServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesMapping.class);
                startActivity(intent);
            }
        });
        lakeShoreFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    private void storeDataInArrays() {
        Cursor cursor = myDB.readAllTransportOrders();
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                order_id.add(cursor.getString(0));
                phone.add(cursor.getString(1));
                type.add(cursor.getString(2));
                number_of_occupants.add(cursor.getString(3));
                date.add(cursor.getString(4));
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void toMaps(View view) {
        Intent intent = new Intent(getApplicationContext(),ServicesMapping.class);
        startActivity(intent);
    }
}