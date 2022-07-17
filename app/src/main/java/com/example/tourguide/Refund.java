package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Refund extends AppCompatActivity {
   final int REQUEST_CODE_SMS = 100;
    Button send;
  EditText phone, transId, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        phone = (EditText) findViewById(R.id.phoneNumber);
        message = (EditText) findViewById(R.id.refundMessage);
        transId = (EditText) findViewById(R.id.transactionId);
        send = (Button) findViewById(R.id.requestBtn);
        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(
                        Refund.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        REQUEST_CODE_SMS);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_SMS){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_SEND);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to send message!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_SMS && resultCode == RESULT_OK && data != null){
            String phoneNumber = phone.getText().toString().trim();
            String transactionId = transId.getText().toString().trim();
            String msg = message.getText().toString().trim();
            String SMS = "\n"+phoneNumber + "\n"+transactionId + "\n"+ msg;


            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber,null,SMS,null,null);
                Toast.makeText(Refund.this,"Message Sent",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(Refund.this,"Failed to send text",Toast.LENGTH_SHORT).show();
            }

         }

        super.onActivityResult(requestCode, resultCode, data);
    }
}