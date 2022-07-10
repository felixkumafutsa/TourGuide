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

public class ServiceProvider extends AppCompatActivity {
    ImageButton transportServices, lakeShoreFacilities, events;
    RecyclerView recyclerView;
    DatabaseAccess myDB;
    ArrayList<String>  id, serviceName, category, type, providerName, providerPhone,  providerEmail, location;
    ServiceProviderAdapter serviceProviderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        Toolbar mtoobar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoobar);
        transportServices = findViewById(R.id.viewMapping);
        lakeShoreFacilities = findViewById(R.id.register);
        events = findViewById(R.id.login);
        recyclerView = findViewById(R.id.serviceProviderRecycler);
        myDB = new DatabaseAccess(ServiceProvider.this);
        id = new ArrayList<>();
        serviceName = new ArrayList<>();
        category = new ArrayList<>();
        type = new ArrayList<>();
        providerName = new ArrayList<>();
        providerPhone = new ArrayList<>();
        providerEmail = new ArrayList<>();
        location = new ArrayList<>();

        storeDataInArrays();

        serviceProviderAdapter = new ServiceProviderAdapter(ServiceProvider.this,this,  id, serviceName, category, type, providerName, providerPhone,  providerEmail, location);
        recyclerView.setAdapter(serviceProviderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ServiceProvider.this));

        transportServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Maps.class);
                startActivity(intent);
            }
        });
        lakeShoreFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesMapping.class);
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
        Cursor cursor = myDB.readAllHotelServices();
        if(cursor.getCount() == 0){

        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                serviceName.add(cursor.getString(1));
                category.add(cursor.getString(2));
                type.add(cursor.getString(3));
                providerName.add(cursor.getString(4));
                providerPhone.add(cursor.getString(5));
                providerEmail.add(cursor.getString(6));
                location.add(cursor.getString(7));
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