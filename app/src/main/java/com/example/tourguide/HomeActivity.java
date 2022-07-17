package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    ImageButton accomodation,lakeshore,hospitals,buses, taxi, routes,map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar mtoobar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoobar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomBar);
        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        accomodation = findViewById(R.id.accomodation);
        lakeshore = findViewById(R.id.lakeshore);
       hospitals = findViewById(R.id.hospa);
       map = findViewById(R.id.mappin);
        buses = findViewById(R.id.publicTransport);
        taxi = findViewById(R.id.refund);
        routes = findViewById(R.id.routes);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.map:
                        Intent intent  = new Intent(getApplicationContext(), ServicesMapping.class);
                        startActivity(intent);
                        break;
                    case R.id.login:
                        Intent intent1  = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent1);
                        break;
                    case R.id.createAccount:
                        Intent intent2  = new Intent(getApplicationContext(), Register.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesMapping.class);
                startActivity(intent);
            }
        });
        routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Maps.class);
                startActivity(intent);
            }
        });
        accomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Services.class);
                startActivity(intent);
            }
        });
        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Services.class);
                startActivity(intent);
            }
        });
        lakeshore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PopularHotels.class);
                startActivity(intent);
            }
        });
        taxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Refund.class);
                startActivity(intent);
            }
        });
       

    }
}