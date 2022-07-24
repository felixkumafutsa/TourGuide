package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentDone extends AppCompatActivity {


    String amountPaid, transID;
    TextView amountPd, trans_id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_done);

        amountPd = findViewById(R.id.amount);
        trans_id = findViewById(R.id.trans_id);

        amountPaid = getIntent().getStringExtra("amount_paid");
        transID = getIntent().getStringExtra("transaction_id");

        amountPd.setText("Amount:"+amountPaid);
        trans_id.setText("Trans ID: "+ transID);

        getIntent().removeExtra("transaction_id");
        getIntent().removeExtra("amount_paid");
    }
}
