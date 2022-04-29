package com.example.tourguide;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tourguide.databinding.ActivityServicesMappingBinding;

public class ServicesMapping extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityServicesMappingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServicesMappingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng blantyre = new LatLng(-15.7861, 35.0058);
        LatLng mzuni = new LatLng(-11.4216,33.9954);
        LatLng katoto = new LatLng( -11.4624,34.0124);
        LatLng lilongwe = new LatLng(-13.9833,33.7833);
        LatLng central = new LatLng(-11.4293,33.9963);
        mMap.addMarker(new MarkerOptions().position(mzuni).title("Hotel Makokola").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.mw1)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mzuni));
        mMap.addMarker(new MarkerOptions().position(central).title("Sunbird").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.mw2)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(central));
        mMap.addMarker(new MarkerOptions().position(lilongwe).title("Cab Man").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.mw1)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lilongwe));
        mMap.addMarker(new MarkerOptions().position(blantyre).title("Blantyre Beach").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.mw2)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(blantyre));
    }
    private BitmapDescriptor bitmapDescriptorFromVector(Context context,int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0,0, vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}