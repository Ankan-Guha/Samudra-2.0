package com.example.samudra20.Home.Main.BeachInfo.WaterQuality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samudra20.R;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class WaterFirst extends AppCompatActivity {


ImageView img;
ImageView img2;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_water_first);  // Ensure this matches your layout file name
            img=findViewById(R.id.image1);
            img.setOnClickListener(v -> {
                Intent intent = new Intent(WaterFirst.this, RealTimeFeed.class);

                startActivity(intent);
            });
        }

        // Method for Image 1 click
        public void openRealTimeFeedActivity(View view) {
            // Intent to navigate to RealTimeFeedActivity
            Intent intent = new Intent(WaterFirst.this, RealTimeFeed.class);
            startActivity(intent);
        }

        // Method for Image 2 click
        public void openDerivedParametersActivity(View view) {
            // Intent to navigate to DerivedParametersActivity
            //Intent intent = new Intent(MainActivity.this, DerivedParametersActivity.class);
            //startActivity(intent);
        }
    }