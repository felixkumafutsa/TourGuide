package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AddService extends AppCompatActivity {
    EditText serviceName, category, type, providerName, providerPhone,  providerEmail, location;
    Button add, selectImage;
    ImageView serviceImage;
    DatabaseAccess DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        serviceName = (EditText) findViewById(R.id.serviceName);
        category = (EditText) findViewById(R.id.serviceCategory);
        type = (EditText) findViewById(R.id.serviceType);
        providerName = (EditText) findViewById(R.id.providerName);
        providerPhone = (EditText) findViewById(R.id.providerPhone);
        providerEmail = (EditText) findViewById(R.id.providerEmail);
        location = (EditText) findViewById(R.id.serviceLocation);
        selectImage = (Button) findViewById(R.id.serviceImageButton);
        add = (Button) findViewById(R.id.addService);
        DB = new DatabaseAccess(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String service = serviceName.getText().toString();
                String cate = category.getText().toString();
                String ty = type.getText().toString();
                String provName = providerName.getText().toString();
                String provPhone = providerPhone.getText().toString();
                String provEmail = providerEmail.getText().toString();
                String locat = location.getText().toString();


                if(service.equals("")||ty.equals("")||cate.equals(""))
                    Toast.makeText(AddService.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(!(provEmail.equals(""))){
                        Boolean insert = DB.addService(service, cate, ty, provName, provPhone,  provEmail, locat);
                        if(insert==true){
                            Toast.makeText(AddService.this, "Service added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Payment.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AddService.this, "Service not added", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(AddService.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    }
