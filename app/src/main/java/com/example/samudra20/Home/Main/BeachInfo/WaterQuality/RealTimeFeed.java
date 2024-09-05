package com.example.samudra20.Home.Main.BeachInfo.WaterQuality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.samudra20.R;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.InputStream;

public class RealTimeFeed extends AppCompatActivity {


        // Declare TextViews for the water quality parameters
        private TextView waterTempValue, salinityValue, currentSpeedValue, phValue, dissolvedOxygenValue,
                dissolvedMethaneValue, pco2AirValue, pco2WaterValue, chlorophyllAValue, phycocyaninValue;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_real_time_feed);

            // Initialize TextViews
            waterTempValue = findViewById(R.id.water_temp_value);
            salinityValue = findViewById(R.id.salinity_value);
            currentSpeedValue = findViewById(R.id.current_speed_value);
            phValue = findViewById(R.id.ph_value);
            dissolvedOxygenValue = findViewById(R.id.dissolved_o2_value);
            dissolvedMethaneValue = findViewById(R.id.dissolved_methane_value);
            pco2AirValue = findViewById(R.id.pco2_air_value);
            pco2WaterValue = findViewById(R.id.pco2_water_value);
            chlorophyllAValue = findViewById(R.id.chlorophyll_a_value);
            phycocyaninValue = findViewById(R.id.phycocyanin_value);

            // Load water quality data from JSON and set the values to the TextViews
            loadWaterQualityData();
        }

        private void loadWaterQualityData() {
            try {
                // Open the water_quality_data.json file located in res/raw folder
                InputStream is = getResources().openRawResource(R.raw.water_quality_data);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                // Convert the buffer into a JSON string
                String json = new String(buffer, "UTF-8");

                // Parse the JSON string into a JSONObject
                JSONObject waterQualityData = new JSONObject(json);

                // Set the data to the TextViews from the JSONObject
                waterTempValue.setText(waterQualityData.getString("waterTemperature") + " °C");
                salinityValue.setText(waterQualityData.getString("salinity") + " PSU");
                currentSpeedValue.setText(waterQualityData.getString("currentSpeed") + " m/s");
                phValue.setText(waterQualityData.getString("pHLevel"));
                dissolvedOxygenValue.setText(waterQualityData.getString("dissolvedOxygen") + " mg/L");
                dissolvedMethaneValue.setText(waterQualityData.getString("dissolvedMethane") + " µmol/L");
                pco2AirValue.setText(waterQualityData.getString("pCO2Air") + " ppm");
                pco2WaterValue.setText(waterQualityData.getString("pCO2Water") + " µatm");
                chlorophyllAValue.setText(waterQualityData.getString("chlorophyllA") + " µg/L");
                phycocyaninValue.setText(waterQualityData.getString("phycocyanin") + " µg/L");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
