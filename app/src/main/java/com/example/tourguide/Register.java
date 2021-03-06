package com.example.tourguide;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText username,emailAddress, password, repassword, phone, service_provided;
    Button signup, signin;
    DatabaseAccess DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.registerName);
        emailAddress = (EditText) findViewById(R.id.registerEmail);
        password = (EditText) findViewById(R.id.registerPassword);
        repassword = (EditText) findViewById(R.id.confirmPassword);
        phone = (EditText) findViewById(R.id.registerPhone);
        service_provided = (EditText) findViewById(R.id.service_type);
        signup = (Button) findViewById(R.id.registerBtn);
        DB = new DatabaseAccess(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String email = emailAddress.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String service = service_provided.getText().toString();


                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.registerUser(username, email , phone, password,service_provided);
                            if(insert==true){
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Profile.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }

    public void toHome(View view) {
        Intent intent = new Intent(getApplicationContext(),Profile.class);
        startActivity(intent);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(getApplicationContext(),Profile.class);
        startActivity(intent);
    }
}