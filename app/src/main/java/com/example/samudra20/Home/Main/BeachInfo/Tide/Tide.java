package com.example.samudra20.Home.Main.BeachInfo.Tide;
public class Tide {


        private String time;
        private String type;
        private double level;

        public Tide(String time, String type, double level) {
            this.time = time;
            this.type = type;
            this.level = level;
        }

        public String getTime() {
            return time;
        }

        public String getType() {
            return type;
        }

        public double getLevel() {
            return level;
        }
    }

