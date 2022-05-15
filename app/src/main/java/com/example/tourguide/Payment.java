package com.example.tourguide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Payment extends AppCompatActivity {
ImageButton airtelMoney, mpamba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        airtelMoney = findViewById(R.id.airtelMoney);
        mpamba = findViewById(R.id.mpamba);
    }

    public void toHome(View view) {
        Intent intent = new Intent(getApplicationContext(),Services.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void dailAirtelMoney(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: *211#"));
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                return;
            }
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void dailMpamba(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: *444#"));

            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                return;
            }

        startActivity(intent);
    }
}