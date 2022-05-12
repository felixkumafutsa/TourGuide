package com.example.tourguide;

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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Services extends AppCompatActivity {
    ImageButton imageButton1, imageButton2, imageButton3;
    RecyclerView recyclerView;
    DatabaseAccess myDB;
    ArrayList<String> service_id, name, category, service_type, provider_name, phone, email, location;
    ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Toolbar mtoobar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoobar);
        imageButton1 = findViewById(R.id.viewMapping);
        imageButton2 = findViewById(R.id.register);
        imageButton3 = findViewById(R.id.login);
        recyclerView = findViewById(R.id.serviceRecycler);
        myDB = new DatabaseAccess(Services.this);
        service_id = new ArrayList<>();
        name = new ArrayList<>();
        category = new ArrayList<>();
        service_type = new ArrayList<>();
        provider_name = new ArrayList<>();
        phone = new ArrayList<>();
        email = new ArrayList<>();
        location = new ArrayList<>();

        storeDataInArrays();

        serviceAdapter = new ServiceAdapter(Services.this,this, service_id, name, category, service_type, provider_name, phone, email, location);
        recyclerView.setAdapter(serviceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Services.this));

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesMapping.class);
                startActivity(intent);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
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
        Cursor cursor = myDB.readAllServices();
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

    public void toMaps(View view) {
        Intent intent = new Intent(getApplicationContext(),ServicesMapping.class);
        startActivity(intent);
    }
}