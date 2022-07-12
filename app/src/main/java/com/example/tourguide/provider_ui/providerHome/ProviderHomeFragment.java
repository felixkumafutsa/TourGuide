package com.example.tourguide.provider_ui.providerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourguide.AddAdmin;
import com.example.tourguide.AddService;
import com.example.tourguide.R;
import com.example.tourguide.ServiceProvider;
import com.example.tourguide.Services;
import com.example.tourguide.ServicesMapping;
import com.example.tourguide.databinding.FragmentHomeBinding;

public class ProviderHomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProviderHomeViewModel homeViewModel =
                new ViewModelProvider(this).get(ProviderHomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button serviceProviders, addService, logout;
        serviceProviders = view.findViewById(R.id.serviceProviders);
        addService = view.findViewById(R.id.addService);
        logout = view.findViewById(R.id.logout);
        ImageButton services = (ImageButton) view.findViewById(R.id.viewHotels);
        ImageButton transactions = (ImageButton) view.findViewById(R.id.viewHotels);
        ImageButton maps = (ImageButton) view.findViewById(R.id.viewMap);
        ImageButton addAdmin= (ImageButton) view.findViewById(R.id.addAdmin);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Services.class));
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ServicesMapping.class));
            }
        });
        addAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddAdmin.class));
            }
        });
        serviceProviders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ServiceProvider.class));
            }
        });
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddService.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Services.class));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}