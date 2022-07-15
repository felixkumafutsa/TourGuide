package com.example.tourguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddService extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
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
        serviceImage = (ImageView) findViewById(R.id.serviceImage);
        selectImage = (Button) findViewById(R.id.serviceImageButton);
        add = (Button) findViewById(R.id.addService);
        DB = new DatabaseAccess(this);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddService.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );

            }
        });
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
                        Boolean insert = DB.addService(service, cate, ty, provName, provPhone,  provEmail,imageViewToByte(serviceImage), locat);
                        if(insert==true){
                            Toast.makeText(AddService.this, "Service added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Services.class);
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
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                serviceImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
