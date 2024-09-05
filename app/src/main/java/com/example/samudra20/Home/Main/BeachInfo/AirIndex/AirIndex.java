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
public class AirIndex extends AppCompatActivity {



        private TextView apiDataTextView;
        private OkHttpClient client;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_air_index);

            // Initialize the TextView to display API data
            apiDataTextView = findViewById(R.id.airIndex);

            // Initialize OkHttpClient
            client = new OkHttpClient();

            // Fetch the data from the API
            fetchAirQualityData();
        }

        private void fetchAirQualityData() {
            String url = "https://air-quality-index-india.p.rapidapi.com/getstatelist";

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("x-rapidapi-host", "https://weatherapi-com.p.rapidapi.com/current.json?q=53.1%2C-0.13")
                    .addHeader("x-rapidapi-key", "38df8820b8msh31579e498bffef1p1052e8jsn8377e37389b5") // Replace with your own key
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e("AirQualityActivity", "Failed to fetch data", e);
                    runOnUiThread(() -> apiDataTextView.setText("Failed to load data"));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseData = response.body().string();
                        Log.d("AirQualityActivity", "Response received: " + responseData);

                        try {
                            // Parse the JSON response
                            JSONObject jsonObject = new JSONObject(responseData);
                            JSONArray stateList = jsonObject.getJSONArray("states");

                            // Build a string to display the data
                            StringBuilder dataStringBuilder = new StringBuilder();
                            dataStringBuilder.append("Air Quality States:\n\n");

                            for (int i = 0; i < stateList.length(); i++) {
                                String state = stateList.getString(i);
                                dataStringBuilder.append(state).append("\n");
                            }

                            // Update the UI with the fetched data on the main thread
                            runOnUiThread(() -> apiDataTextView.setText(dataStringBuilder.toString()));
                        } catch (JSONException e) {
                            Log.e("AirQualityActivity", "JSON parsing error", e);
                            runOnUiThread(() -> apiDataTextView.setText("Error parsing data"));
                        }
                    } else {
                        Log.e("AirQualityActivity", "Unsuccessful response");
                        runOnUiThread(() -> apiDataTextView.setText("Failed to load data"));
                    }
                }
            });
        }
    }
