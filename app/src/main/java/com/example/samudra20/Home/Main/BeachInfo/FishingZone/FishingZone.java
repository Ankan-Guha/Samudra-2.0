package com.example.samudra20.Home.Main.BeachInfo.FishingZone;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.samudra20.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FishingZone extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_zone);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add markers for fishing areas around Kochi
        LatLng kochiCity = new LatLng(9.931233, 76.267303);
        LatLng cochinPort = new LatLng(9.5800, 76.1400);
        LatLng chellanam = new LatLng(9.8567, 76.2665);
        LatLng kodamthuruth = new LatLng(9.9167, 76.2167);
        LatLng thuravoorThekku = new LatLng(9.8000, 76.3333);
        LatLng vettakkal = new LatLng(9.7833, 76.2500);
        LatLng kadakkarappally = new LatLng(9.7167, 76.3333);
        LatLng kuzhuppilly = new LatLng(10.0916, 76.1934);
        LatLng mararikulam = new LatLng(9.6333, 76.3333);

        // Add markers with titles for each fishing area
        mMap.addMarker(new MarkerOptions().position(kochiCity).title("Kochi City"));
        mMap.addMarker(new MarkerOptions().position(cochinPort).title("Cochin Port"));
        mMap.addMarker(new MarkerOptions().position(chellanam).title("Chellanam"));
        mMap.addMarker(new MarkerOptions().position(kodamthuruth).title("Kodamthuruth"));
        mMap.addMarker(new MarkerOptions().position(thuravoorThekku).title("Thuravoor Thekku"));
        mMap.addMarker(new MarkerOptions().position(vettakkal).title("Vettakkal"));
        mMap.addMarker(new MarkerOptions().position(kadakkarappally).title("Kadakkarappally"));
        mMap.addMarker(new MarkerOptions().position(kuzhuppilly).title("Kuzhuppilly"));
        mMap.addMarker(new MarkerOptions().position(mararikulam).title("Mararikulam"));

        // Move the camera to Kochi and adjust zoom to show all locations
        LatLng initialLocation = new LatLng(9.931233, 76.267303);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 10));
    }
}