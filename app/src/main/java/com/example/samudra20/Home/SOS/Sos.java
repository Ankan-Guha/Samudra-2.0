package com.example.samudra20.Home.SOS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samudra20.R;





import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

    public class Sos extends AppCompatActivity {

        private static final int REQUEST_PERMISSIONS = 1;
        private String emergencyNumber = "911"; // Replace with your local emergency number
        private LocationManager locationManager;
        private String locationMessage = "Location not available";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_sos);

            // Request necessary permissions
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSIONS);
            } else {
                // Permissions already granted, proceed with the call and sending location
                makeEmergencyCall();
            }
        }

        private void makeEmergencyCall() {
            // Get location before making the call
            getLocation();

            // Make the emergency call
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + emergencyNumber));
            try {
                startActivity(callIntent);
            } catch (SecurityException e) {
                Toast.makeText(this, "Permission denied, cannot make the call.", Toast.LENGTH_SHORT).show();
            }

            // Send SMS with location
            sendLocationSMS();
        }

        private void getLocation() {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationMessage = "Emergency! I am at: https://maps.google.com/?q=" + latitude + "," + longitude;
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {}

                @Override
                public void onProviderEnabled(String provider) {}

                @Override
                public void onProviderDisabled(String provider) {}
            });

            // Get last known location
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                locationMessage = "Emergency! I am at: https://maps.google.com/?q=" + latitude + "," + longitude;
            }
        }

        private void sendLocationSMS() {
            SmsManager smsManager = SmsManager.getDefault();
            try {
                smsManager.sendTextMessage(emergencyNumber, null, locationMessage, null, null);
                Toast.makeText(this, "Location sent via SMS.", Toast.LENGTH_SHORT).show();
            } catch (SecurityException e) {
                Toast.makeText(this, "Permission denied, cannot send SMS.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == REQUEST_PERMISSIONS) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissions granted, proceed with the call and sending location
                    makeEmergencyCall();
                } else {
                    // Permission denied
                    Toast.makeText(this, "Permissions denied, cannot proceed.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }