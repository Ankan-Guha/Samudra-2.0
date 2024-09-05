package com.example.samudra20.Home.Main.BeachInfo.Tide;

import java.util.ArrayList;

public class TideData {
    private String location;
    private String timeZone;
    private ArrayList<Tide> tides;

    public String getLocation() {
        return location;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public ArrayList<Tide> getTides() {
        return tides;
    }
}

