package model;

import java.util.ArrayList;

public class Plane {
    private String type;
    private String callSign;
    private ArrayList<DateTime> avails;
    private int hourlyRentalRate;
    private int hourlyFuelRate;
    private PlaneDocuments pd;
    private double fuelAmount;
    private double maxFuel;


    // EFFECT: create a plane profile with empty plane type, call sign, availability for booking,
    // hourly rental and fuel rate, fuel amount in plane, and plane documents
    public Plane() {
        type = "";
        callSign = "";
        avails = new ArrayList<>();
        hourlyRentalRate = 0;
        hourlyFuelRate = 0;
        pd = new PlaneDocuments();
        fuelAmount = 0.0;
        maxFuel = 0.0;
    }

    // REQUIRES: 0 < amount <= maxFuel, and fuelAmount + amount <= maxFuel
    public void addFuel(double amount) {
        // stub
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public ArrayList<DateTime> getAvails() {
        return avails;
    }

    public int getHourlyFuelRate() {
        return hourlyFuelRate;
    }

    public int getHourlyRentalRate() {
        return hourlyRentalRate;
    }

    public PlaneDocuments getPd() {
        return pd;
    }

    public String getCallSign() {
        return callSign;
    }

    public String getType() {
        return type;
    }

    public void setAvails(ArrayList<DateTime> avails) {
        this.avails = avails;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public void setHourlyFuelRate(int hourlyFuelRate) {
        this.hourlyFuelRate = hourlyFuelRate;
    }

    public void setHourlyRentalRate(int hourlyRentalRate) {
        this.hourlyRentalRate = hourlyRentalRate;
    }

    public void setPd(PlaneDocuments pd) {
        this.pd = pd;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setMaxFuel(double maxFuel) {
        this.maxFuel = maxFuel;
    }
}
