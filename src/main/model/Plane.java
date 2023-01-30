package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Plane {
    private String type;
    private String callSign;
    private ArrayList<Availability> avails;
    private int hourlyRentalRate;
    private int hourlyFuelRate;
    private PlaneDocuments pd;


    // EFFECT: create a plane profile with plane type, call sign, availability for booking,
    // hourly rental and fuel rate, and plane documents
    public Plane() {
        // stub
    }

    public ArrayList<Availability> getAvails() {
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

    public void setAvails(ArrayList<Availability> avails) {
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
}
