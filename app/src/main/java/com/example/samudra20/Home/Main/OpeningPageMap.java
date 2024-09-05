package com.example.samudra20.Home.Main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.samudra20.Home.Drawer.HelpDesk;
import com.example.samudra20.Home.Main.BeachInfo.AirIndex.AirIndex;
import com.example.samudra20.Home.SOS.Sos;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.samudra20.Home.Main.BeachInfo.BeachInfoActivity;
import com.example.samudra20.Home.Main.DataSetForBeaches.DataBeaches;
import com.example.samudra20.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class OpeningPageMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_REQUEST_CODE = 101;
    private SearchView searchView;
    private List<Marker> defaultMarkers = new ArrayList<>();
    private DataBeaches ds;
    private Button b,hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page_map);

        // Initialize Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        b=findViewById(R.id.SOS);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AirIndex activity when the airIndex card is clicked
                Intent intent = new Intent(OpeningPageMap.this, Sos.class);
                startActivity(intent);
            }
        });
        hd=findViewById(R.id.Help);
        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AirIndex activity when the airIndex card is clicked
                Intent intent = new Intent(OpeningPageMap.this, HelpDesk.class);
                startActivity(intent);
            }
        });


        // Initialize SearchView
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBeaches(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Initialize Location Client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

        // Initialize DataBeaches
        ds = new DataBeaches();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Initialize mMap with the GoogleMap instance
        mMap = googleMap;

        if (mMap != null) {
            // Set OnMarkerClickListener after mMap is initialized
            mMap.setOnMarkerClickListener(marker -> {
                LatLng position = marker.getPosition();
                String beachName = marker.getTitle();
                Intent intent = new Intent(OpeningPageMap.this, BeachInfoActivity.class);
                intent.putExtra("lat", position.latitude);
                intent.putExtra("lng", position.longitude);
                intent.putExtra("beach_name", beachName);
                startActivity(intent);
                return false;
            });

            // Add default markers
            addDefaultMarkers();
        } else {
            // Handle the case where the map is not initialized
            Toast.makeText(this, "Map not available", Toast.LENGTH_SHORT).show();
        }
    }


    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));
                mMap.addMarker(new MarkerOptions().position(userLocation).title("You are here"));
                // Zoom into the user's location
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
            } else {
                Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchBeaches(String query) {
        DataBeaches ds = new DataBeaches(); // Assuming this is your data source
        List<LatLng> searchResults = new ArrayList<>();
        List<String> searchResultsNames = new ArrayList<>();

        // Clear existing markers
        for (Marker marker : defaultMarkers) {
            marker.remove();
        }
        defaultMarkers.clear();

        // Search logic (matching beach names with the query)
        for (int i = 0; i < ds.beachNames.size(); i++) {
            String beachName = ds.beachNames.get(i);
            if (beachName.toLowerCase().contains(query.toLowerCase())) {
                LatLng beachLocation = ds.beaches.get(i);
                searchResults.add(beachLocation);
                searchResultsNames.add(beachName);
            }
        }

        // Add new markers for the search results
        int i = 0;
        for (LatLng location : searchResults) {
            Bitmap beachIcon = getBitmapFromVectorDrawable(R.drawable.baseline_beach_access_24);
            Marker marker;
            if (beachIcon != null) {
                marker = mMap.addMarker(new MarkerOptions()
                        .position(location)
                        .title(searchResultsNames.get(i++))
                        .icon(BitmapDescriptorFactory.fromBitmap(beachIcon)));
            } else {
                marker = mMap.addMarker(new MarkerOptions().position(location).title(searchResultsNames.get(i++)));
            }
            if (marker != null) {
                defaultMarkers.add(marker);
            }
        }

        // Zoom into the first result if any
        if (!searchResults.isEmpty()) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchResults.get(0), 15));
        }
    }

    private void addDefaultMarkers() {
        DataBeaches ds = new DataBeaches(); // Ensure you have a valid DataSet class
        int i = 0;
        for (LatLng beach : ds.beaches) {
            Bitmap beachIcon = getBitmapFromVectorDrawable(R.drawable.baseline_beach_access_24);
            Marker marker;
            if (beachIcon != null) {
                marker = mMap.addMarker(new MarkerOptions()
                        .position(beach)
                        .title(ds.beachNames.get(i++))
                        .icon(BitmapDescriptorFactory.fromBitmap(beachIcon)));
            } else {
                marker = mMap.addMarker(new MarkerOptions().position(beach).title(ds.beachNames.get(i++)));
            }
            if (marker != null) {
                defaultMarkers.add(marker);
            }
        }
    }

    private Bitmap getBitmapFromVectorDrawable(int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        if (drawable == null) return null;

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
