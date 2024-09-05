package com.example.samudra20.Home.Main.BeachInfo.WaterQuality;

public class Water{
    private float waterTemperature;
    private float salinity;
    private float currentSpeed;
    private float pHLevel;
    private float dissolvedOxygen;
    private float dissolvedMethane;
    private float pCO2Air;
    private float pCO2Water;
    private float chlorophyllA;
    private float phycoerythrin;
    private float phycocyanin;
    private float turbidity;
    private float coloredDissolvedOrganicMatter;
    private float scattering;

    // Constructor
    public Water(float waterTemperature, float salinity, float currentSpeed, float pHLevel, float dissolvedOxygen, float dissolvedMethane, float pCO2Air, float pCO2Water, float chlorophyllA, float phycoerythrin, float phycocyanin, float turbidity, float coloredDissolvedOrganicMatter, float scattering) {
        this.waterTemperature = waterTemperature;
        this.salinity = salinity;
        this.currentSpeed = currentSpeed;
        this.pHLevel = pHLevel;
        this.dissolvedOxygen = dissolvedOxygen;
        this.dissolvedMethane = dissolvedMethane;
        this.pCO2Air = pCO2Air;
        this.pCO2Water = pCO2Water;
        this.chlorophyllA = chlorophyllA;
        this.phycoerythrin = phycoerythrin;
        this.phycocyanin = phycocyanin;
        this.turbidity = turbidity;
        this.coloredDissolvedOrganicMatter = coloredDissolvedOrganicMatter;
        this.scattering = scattering;
    }

    // Getters and Setters
    public float getWaterTemperature() { return waterTemperature; }
    public void setWaterTemperature(float waterTemperature) { this.waterTemperature = waterTemperature; }

    public float getSalinity() { return salinity; }
    public void setSalinity(float salinity) { this.salinity = salinity; }

    public float getCurrentSpeed() { return currentSpeed; }
    public void setCurrentSpeed(float currentSpeed) { this.currentSpeed = currentSpeed; }

    public float getpHLevel() { return pHLevel; }
    public void setpHLevel(float pHLevel) { this.pHLevel = pHLevel; }

    public float getDissolvedOxygen() { return dissolvedOxygen; }
    public void setDissolvedOxygen(float dissolvedOxygen) { this.dissolvedOxygen = dissolvedOxygen; }

    public float getDissolvedMethane() { return dissolvedMethane; }
    public void setDissolvedMethane(float dissolvedMethane) { this.dissolvedMethane = dissolvedMethane; }

    public float getpCO2Air() { return pCO2Air; }
    public void setpCO2Air(float pCO2Air) { this.pCO2Air = pCO2Air; }

    public float getpCO2Water() { return pCO2Water; }
    public void setpCO2Water(float pCO2Water) { this.pCO2Water = pCO2Water; }

    public float getChlorophyllA() { return chlorophyllA; }
    public void setChlorophyllA(float chlorophyllA) { this.chlorophyllA = chlorophyllA; }

    public float getPhycoerythrin() { return phycoerythrin; }
    public void setPhycoerythrin(float phycoerythrin) { this.phycoerythrin = phycoerythrin; }

    public float getPhycocyanin() { return phycocyanin; }
    public void setPhycocyanin(float phycocyanin) { this.phycocyanin = phycocyanin; }

    public float getTurbidity() { return turbidity; }
    public void setTurbidity(float turbidity) { this.turbidity = turbidity; }

    public float getColoredDissolvedOrganicMatter() { return coloredDissolvedOrganicMatter; }
    public void setColoredDissolvedOrganicMatter(float coloredDissolvedOrganicMatter) { this.coloredDissolvedOrganicMatter = coloredDissolvedOrganicMatter; }

    public float getScattering() { return scattering; }
    public void setScattering(float scattering) { this.scattering = scattering; }
}

