package com.example.samudra20.Home.Main.BeachInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.samudra20.Home.Main.BeachInfo.AirIndex.AirIndex;
import com.example.samudra20.Home.Main.BeachInfo.FishingZone.FishingZone;
//import com.example.samudra20.Home.Main.BeachInfo.Tides.TideActivity;
import com.example.samudra20.Home.Main.BeachInfo.Tide.TideActivity;
import com.example.samudra20.Home.Main.BeachInfo.WaterQuality.WaterFirst;
import com.example.samudra20.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BeachInfoActivity extends AppCompatActivity {

    private TextView beachName;
    private CardView airIndex,water, fishingZone,tsunamiWarning, tides;
    private ImageView beachPhoto;
    private TextView  oxygenLevel, waterQuality;
    private CardView weatherCard;
    private final String API_ACCESS_KEY = "YOUR_API_ACCESS_KEY"; // Replace with your actual API key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_info);



        // Initialize UI elements
        beachName = findViewById(R.id.beach_name);
        //beachPhoto = findViewById(R.id.beach_photo);


        airIndex = findViewById(R.id.airIndexCard);
        double latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        double longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);

        // Set click listener on airIndex card to open AirIndex activity
        airIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AirIndex activity when the airIndex card is clicked
                Intent intent = new Intent(BeachInfoActivity.this, AirIndex.class);
                intent.putExtra("LATITUDE", latitude); // Pass latitude
                intent.putExtra("LONGITUDE", longitude); // Pass longitude
                startActivity(intent);
            }
        });
        tides = findViewById(R.id.tides);

        // Set click listener on airIndex card to open AirIndex activity
        tides.setOnClickListener(v -> {
            Intent intent = new Intent(BeachInfoActivity.this, TideActivity.class);
            intent.putExtra("LATITUDE", latitude); // Pass latitude
            intent.putExtra("LONGITUDE", longitude); // Pass longitude
            startActivity(intent);
        });
        tsunamiWarning = findViewById(R.id.tsunami);

        // Set click listener on airIndex card to open AirIndex activity
        tsunamiWarning.setOnClickListener(v -> {
            Intent intent = new Intent(BeachInfoActivity.this, tsAc.class);

            startActivity(intent);
        });

        water = findViewById(R.id.water);

        // Set click listener on airIndex card to open AirIndex activity
        water.setOnClickListener(v -> {
            Intent intent = new Intent(BeachInfoActivity.this, WaterFirst.class);
            intent.putExtra("LATITUDE", latitude); // Pass latitude
            intent.putExtra("LONGITUDE", longitude); // Pass longitude
            startActivity(intent);
        });

        fishingZone = findViewById(R.id.fishing);

        // Set click listener on airIndex card to open AirIndex activity
        fishingZone.setOnClickListener(v -> {
            Intent intent = new Intent(BeachInfoActivity.this, FishingZone.class);
            intent.putExtra("LATITUDE", latitude); // Pass latitude
            intent.putExtra("LONGITUDE", longitude); // Pass longitude
            startActivity(intent);
        });
        oxygenLevel = findViewById(R.id.oxygenLevel);


        //waterQuality = findViewById(R.id.TextWater);

        // weatherCard = findViewById(R.id.weather); // Uncomment if used

        // Get beach details from Intent


        Intent intent = getIntent();
        String beachName = intent.getStringExtra("beach_name");

        // Find the TextView and set the beach name
        TextView beachNameTextView = findViewById(R.id.beach_name); // Replace with your TextView ID
        beachNameTextView.setText(beachName);
        // Fetch data from APIs
        //fetchAirQualityData(latitude, longitude);
        fetchTsunamiData();
        fetchStormSurgeData();
        fetchHighWaveAlerts();
        fetchSwellSurgeAlerts();
        fetchCoastalCurrentAlerts();

        // Fetch a real-time photo of the beach
        fetchBeachPhoto(latitude, longitude);
    }

    private void fetchAirQualityData(double lat, double lng) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://air-quality.p.rapidapi.com/history/airquality?lat=" + lat + "&lon=" + lng;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Host", "https://weatherapi-com.p.rapidapi.com/current.json?q=53.1%2C-0.13")
                .addHeader("X-RapidAPI-Key","38df8820b8msh31579e498bffef1p1052e8jsn8377e37389b5")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();

                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);

                    // Safely handle the JSON data
                    JsonElement dataElement = jsonObject.get("data");
                    if (dataElement != null && dataElement.isJsonArray()) {
                        JsonArray dataArray = dataElement.getAsJsonArray();
                        if (dataArray.size() > 0) {
                            JsonObject firstRecord = dataArray.get(0).getAsJsonObject();
                            JsonElement aqiElement = firstRecord.get("aqi");

                            // Check if the AQI element is not null
                            String airQuality = (aqiElement != null && !aqiElement.isJsonNull()) ? aqiElement.getAsString() : "N/A";

                            // Update UI on the main thread
                            //runOnUiThread(() -> airIndex.setText("Air Quality Index: " + airQuality));
                        }
                    }
                }
            }
        });
    }


    private void fetchTsunamiData() {
        String url = "https://gemini.incois.gov.in/incoisapi/rest/tsunami";

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", API_ACCESS_KEY)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
                    // Process JSON data here
                    runOnUiThread(() -> {
                        // Update UI with tsunami data
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void fetchStormSurgeData() {
        String url = "https://gemini.incois.gov.in/incoisapi/rest/stormsurgelatest";

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", API_ACCESS_KEY)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
                    // Process JSON data here
                    runOnUiThread(() -> {
                        // Update UI with storm surge data
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void fetchHighWaveAlerts() {
        String url = "https://gemini.incois.gov.in/incoisapi/rest/hwalatestgeo";

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", API_ACCESS_KEY)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
                    // Process JSON data here
                    runOnUiThread(() -> {
                        // Update UI with high wave alerts
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void fetchSwellSurgeAlerts() {
        String url = "https://gemini.incois.gov.in/incoisapi/rest/ssalatestgeo";

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", API_ACCESS_KEY)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
                    // Process JSON data here
                    runOnUiThread(() -> {
                        // Update UI with swell surge alerts
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void fetchCoastalCurrentAlerts() {
        String url = "https://gemini.incois.gov.in/incoisapi/rest/currentslatestgeo";

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", API_ACCESS_KEY)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
                    // Process JSON data here
                    runOnUiThread(() -> {
                        // Update UI with coastal current alerts
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void fetchBeachPhoto(double latitude, double longitude) {
        // Implement the logic to fetch and display the real-time photo of the beach using Google APIs
    }

}
