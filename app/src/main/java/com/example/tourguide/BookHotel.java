package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookHotel extends AppCompatActivity {
    EditText phone, type, number_of_occupants, etdate;
    final Calendar myCalendar= Calendar.getInstance();
    Button signup, signin;
    DatabaseAccess DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);
            phone = (EditText) findViewById(R.id.bookerPhone);
            type = (EditText) findViewById(R.id.roomType);
            number_of_occupants = (EditText) findViewById(R.id.numberOfPeople);
            etdate = (EditText) findViewById(R.id.date);
            signup = (Button) findViewById(R.id.bookHotel);
            DB = new DatabaseAccess(this);


        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookHotel.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phon = phone.getText().toString();
                    String typ = type.getText().toString();
                    String numberOfOccupants = number_of_occupants.getText().toString();
                    String dt = etdate.getText().toString();


                    if(phone.equals("")||type.equals("")||date.equals(""))
                        Toast.makeText(BookHotel.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else{
                        if(!(number_of_occupants.equals(""))){
                               Boolean insert = DB.bookHotel(phon, typ, numberOfOccupants, dt);
                                if(insert==true){
                                    //Boolean insert1 = DB.addTransaction(service_provider,date);
                                    Toast.makeText(BookHotel.this, "Booking placed successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Payment.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(BookHotel.this, "Booking failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(BookHotel.this, "Number of Occupants required", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            });

        }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etdate.setText(dateFormat.format(myCalendar.getTime()));
    }
    public void toServices(View view) {
        Intent intent = new Intent(getApplicationContext(),Services.class);
        startActivity(intent);
    }
}

