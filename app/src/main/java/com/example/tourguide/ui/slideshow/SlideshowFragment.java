package com.example.tourguide.ui.slideshow;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourguide.About;
import com.example.tourguide.AddService;
import com.example.tourguide.Commisions;
import com.example.tourguide.HomeActivity;
import com.example.tourguide.HotelOrders;
import com.example.tourguide.R;
import com.example.tourguide.ServicesMapping;
import com.example.tourguide.Transactions;
import com.example.tourguide.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    public void onViewCreated(View view, Bundle savedInstanceState){
        Button about, addService, logout, send;
        EditText phone,message;
        phone = view.findViewById(R.id.phone);
        message = view.findViewById(R.id.msgSendcontent);
        send = view.findViewById(R.id.sendSms);
        addService = view.findViewById(R.id.registerService);
        about = view.findViewById(R.id.about);
        ImageButton revenue = (ImageButton) view.findViewById(R.id.revenue);
        ImageButton servicesAndOrders = (ImageButton) view.findViewById(R.id.myServices);
        ImageButton refunds = (ImageButton) view.findViewById(R.id.ordersCard);
        ImageButton commission = (ImageButton) view.findViewById(R.id.commisionsCardBtn );

        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if ((getContext().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)){
                        String phoneNumber = phone.getText().toString().trim();
                        String SMS = message.getText().toString().trim();

                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber,null,SMS,null,null);
                            Toast.makeText(getContext(),"Message Sent",Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getContext(),"Failed to send text",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
        revenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Transactions.class));
            }
        });
        servicesAndOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HotelOrders.class));
            }
        });
        refunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ServicesMapping.class));
            }
        });
        commission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Commisions.class));
            }
        });
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddService.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), About.class));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
