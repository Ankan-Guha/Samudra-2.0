package com.example.samudra20.Home.Main.BeachInfo;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.samudra20.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class tsAc extends FragmentActivity implements OnMapReadyCallback {

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

        LatLng kochiEvacCenter = new LatLng(9.9827, 76.2999); // Example evacuation center in Kochi
        LatLng mattancherryEvacCenter = new LatLng(9.9530, 76.2641); // Mattancherry evacuation center
        LatLng fortKochiEvacCenter = new LatLng(9.9656, 76.2423); // Fort Kochi evacuation center
        LatLng edapallyEvacCenter = new LatLng(10.0159, 76.3079); // Edapally evacuation center
        LatLng tripunithuraEvacCenter = new LatLng(9.9317, 76.3458); // Tripunithura evacuation center
        LatLng kalamasseryEvacCenter = new LatLng(10.0480, 76.3087); // Kalamassery evacuation center

        // Add markers for hospitals around Kochi (on land)
        LatLng generalHospital = new LatLng(9.9715, 76.2796); // General Hospital, Kochi
        LatLng amritaHospital = new LatLng(10.0286, 76.3089); // Amrita Hospital
        LatLng lakeshoreHospital = new LatLng(9.9301, 76.3102); // Lakeshore Hospital
        LatLng ernakulamMedicalCentre = new LatLng(10.0004, 76.3155); // Ernakulam Medical Centre
        LatLng asterMedcity = new LatLng(10.0464, 76.3152); // Aster Medcity

        // Add markers with titles for each evacuation center and hospital
        mMap.addMarker(new MarkerOptions().position(kochiEvacCenter).title("Kochi Evacuation Center"));
        mMap.addMarker(new MarkerOptions().position(mattancherryEvacCenter).title("Mattancherry Evacuation Center"));
        mMap.addMarker(new MarkerOptions().position(fortKochiEvacCenter).title("Fort Kochi Evacuation Center"));
        mMap.addMarker(new MarkerOptions().position(edapallyEvacCenter).title("Edapally Evacuation Center"));
        mMap.addMarker(new MarkerOptions().position(tripunithuraEvacCenter).title("Tripunithura Evacuation Center"));
        mMap.addMarker(new MarkerOptions().position(kalamasseryEvacCenter).title("Kalamassery Evacuation Center"));

        mMap.addMarker(new MarkerOptions().position(generalHospital).title("General Hospital, Kochi"));
        mMap.addMarker(new MarkerOptions().position(amritaHospital).title("Amrita Hospital"));
        mMap.addMarker(new MarkerOptions().position(lakeshoreHospital).title("Lakeshore Hospital"));
        mMap.addMarker(new MarkerOptions().position(ernakulamMedicalCentre).title("Ernakulam Medical Centre"));
        mMap.addMarker(new MarkerOptions().position(asterMedcity).title("Aster Medcity"));

        // Move the camera to Kochi and adjust zoom to show all locations
        LatLng initialLocation = new LatLng(9.931233, 76.267303);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 20));
    }
}