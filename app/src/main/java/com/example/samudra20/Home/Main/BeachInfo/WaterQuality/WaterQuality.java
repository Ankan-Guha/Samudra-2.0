package com.example.samudra20.Home.Main.BeachInfo.WaterQuality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.samudra20.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WaterQuality extends AppCompatActivity {

    private TextView currentSpeed, currentDirection, ph, salinity, temperature, dissolvedOxygen, dissolvedMethane, pco2Air, pco2Water, chlorophyll, phycocyanin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_quality);

        // Initialize TextViews for displaying water quality data
        currentSpeed = findViewById(R.id.currentSpeed);
        currentDirection = findViewById(R.id.currentDirection);
        ph = findViewById(R.id.ph);
        salinity = findViewById(R.id.salinity);
        temperature = findViewById(R.id.temperature);
        dissolvedOxygen = findViewById(R.id.dissolvedOxygen);
        dissolvedMethane = findViewById(R.id.dissolvedMethane);
        pco2Air = findViewById(R.id.pco2Air);
        pco2Water = findViewById(R.id.pco2Water);
        chlorophyll = findViewById(R.id.chlorophyll);
        phycocyanin = findViewById(R.id.phycocyanin);

        // Get latitude and longitude from Intent
        double latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        double longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);

        // Fetch water quality data
        fetchWaterQualityData(latitude, longitude);
    }

    private void fetchWaterQualityData(double lat, double lon) {
        // Construct the URL with latitude and longitude or other parameters if needed
        String url = "https://gemini.incois.gov.in/OceanDataAPI/api/wqns/"; // Update this URL if needed

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("API-Key", " 446d183e64e64e8eb4bca1407ab02a89") // Replace with your actual API key
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    runOnUiThread(() -> parseWaterQualityData(responseData));
                }
            }
        });
    }

    private void parseWaterQualityData(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);

            // Parse the required fields from the JSON response
            currentSpeed.setText("Current Speed: " + jsonObject.optString("currentspeed", "N/A"));
            currentDirection.setText("Current Direction: " + jsonObject.optString("currentdirection", "N/A"));
            ph.setText("pH Level: " + jsonObject.optString("ph", "N/A"));
            salinity.setText("Salinity: " + jsonObject.optString("salinity", "N/A"));
            temperature.setText("Temperature: " + jsonObject.optString("temperature", "N/A"));
            dissolvedOxygen.setText("Dissolved Oxygen: " + jsonObject.optString("dissolvedoxygen", "N/A"));
            dissolvedMethane.setText("Dissolved Methane: " + jsonObject.optString("dissolvedmethane", "N/A"));
            pco2Air.setText("pCO2 Air: " + jsonObject.optString("pco2air", "N/A"));
            pco2Water.setText("pCO2 Water: " + jsonObject.optString("pco2water", "N/A"));
            chlorophyll.setText("Chlorophyll: " + jsonObject.optString("chlorophyll", "N/A"));
            phycocyanin.setText("Phycocyanin: " + jsonObject.optString("phycocyanin", "N/A"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
