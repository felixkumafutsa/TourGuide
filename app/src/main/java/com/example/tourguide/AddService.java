package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddService extends AppCompatActivity {
    EditText phone, type, number_of_occupants, date;
    Button signup, signin;
    DatabaseAccess DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        phone = (EditText) findViewById(R.id.bookerPhone);
        type = (EditText) findViewById(R.id.roomType);
        number_of_occupants = (EditText) findViewById(R.id.numberOfPeople);
        date = (EditText) findViewById(R.id.date);
        signup = (Button) findViewById(R.id.bookHotel);
        DB = new DatabaseAccess(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phon = phone.getText().toString();
                String typ = type.getText().toString();
                String numberOfOccupants = number_of_occupants.getText().toString();
                String dt = date.getText().toString();


                if(phone.equals("")||type.equals("")||date.equals(""))
                    Toast.makeText(AddService.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(!(number_of_occupants.equals(""))){
                        Boolean insert = DB.bookHotel(phon, typ, numberOfOccupants, dt);
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
