package com.example.samudra20.Home.Main.BeachInfo.AirIndex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.samudra20.R;

import okhttp3.OkHttpClient;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.InputStream;
import com.example.samudra20.R;
public class AirIndex extends AppCompatActivity {



        // Declare TextViews for the air quality parameters
        private TextView airIndexValue, o2LevelValue, co2LevelValue, windSpeedValue, humidityValue;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_air_index);

            // Initialize TextViews
            airIndexValue = findViewById(R.id.air_index_value);
            o2LevelValue = findViewById(R.id.o2_level_value);
            co2LevelValue = findViewById(R.id.co2_level_value);
            windSpeedValue = findViewById(R.id.wind_speed_value);
            humidityValue = findViewById(R.id.humidity_value);

            // Load air quality data from JSON and set the values to the TextViews
            loadAirQualityData();
        }

        private void loadAirQualityData() {
            try {
                // Open the air_quality_data.json file located in res/raw folder
                InputStream is = getResources().openRawResource(R.raw.air_quality_data);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                // Convert the buffer into a JSON string
                String json = new String(buffer, "UTF-8");

                // Parse the JSON string into a JSONObject
                JSONObject airQualityData = new JSONObject(json);

                // Set the data to the TextViews from the JSONObject
                airIndexValue.setText(airQualityData.getString("airIndex"));
                o2LevelValue.setText(airQualityData.getString("o2Level") + " %");
                co2LevelValue.setText(airQualityData.getString("co2Level") + " ppm");
                windSpeedValue.setText(airQualityData.getString("windSpeed") + " m/s");
                humidityValue.setText(airQualityData.getString("humidity") + " %");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
