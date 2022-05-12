package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAdmin extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup;
    DatabaseAccess DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        username = (EditText) findViewById(R.id.registerName);
        password = (EditText) findViewById(R.id.registerPassword);
        repassword = (EditText) findViewById(R.id.confirmPassword);
        signup = (Button) findViewById(R.id.registerBtn);
        DB = new DatabaseAccess(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(AddAdmin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.registerAdmin(user, pass);
                            if(insert==true){
                                Toast.makeText(AddAdmin.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Profile.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AddAdmin.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(AddAdmin.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AddAdmin.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }


    public void toLogin(View view) {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }
}
