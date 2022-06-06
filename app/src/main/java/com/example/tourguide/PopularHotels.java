package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PopularHotels extends AppCompatActivity {
    Toolbar mtoobar;
    RecyclerView recyclerView;
    DatabaseAccess myDB;
    ArrayList<String> service_id, name, category, service_type, provider_name, phone, email, location;
    ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_hotels);

        mtoobar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoobar);

        recyclerView = findViewById(R.id.hotelRecycler);
        myDB = new DatabaseAccess(PopularHotels.this);
        service_id = new ArrayList<>();
        name = new ArrayList<>();
        category = new ArrayList<>();
        service_type = new ArrayList<>();
        provider_name = new ArrayList<>();
        phone = new ArrayList<>();
        email = new ArrayList<>();
        location = new ArrayList<>();

        storeDataInArrays();

        serviceAdapter = new ServiceAdapter(PopularHotels.this,this, service_id, name, category, service_type, provider_name, phone, email, location);
        recyclerView.setAdapter(serviceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PopularHotels.this));
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
                service_id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                category.add(cursor.getString(2));
                service_type.add(cursor.getString(3));
                phone.add(cursor.getString(4));
                email.add(cursor.getString(5));
                location.add(cursor.getString(6));
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}