package com.example.samudra20.Home.Main.BeachInfo.Tide;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.samudra20.Home.Main.BeachInfo.Tide.Tide;
import com.example.samudra20.Home.Main.BeachInfo.Tide.TideAdaptor;
import com.example.samudra20.Home.Main.BeachInfo.Tide.TideData;
import com.example.samudra20.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TideActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTides;
    private TideAdaptor tideAdapter;
    private ArrayList<Tide> tideList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tide);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerViewTides = findViewById(R.id.recyclerViewTides);
        recyclerViewTides.setLayoutManager(new LinearLayoutManager(this));

        tideList = new ArrayList<>();
        tideAdapter = new TideAdaptor(tideList);
        recyclerViewTides.setAdapter(tideAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTideData();
            }
        });

        loadTideData();
    }


    private void loadTideData() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.tides_data);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            Type tideDataType = new TypeToken<TideData>(){}.getType();
            TideData tideData = gson.fromJson(json, tideDataType);

            if (tideData != null && tideData.getTides() != null) {
                tideList.clear();
                tideList.addAll(tideData.getTides());
                tideAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "No tides data found", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show();
        }
    }
}
