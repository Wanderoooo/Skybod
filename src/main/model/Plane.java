package model;

// Represents a plane (aircraft) with type (i.e. Cessna aircraft), call sign (i.e. C-GUUY),
// availability for booking, hourly rental & fuel rate (in CAD$), its plane documents
// (such as insurance, flight log, & weight info), its current fuel amount (in US Gallons),
// and maximum fuel capacity (in US Gallons). Fuel can be added by given amount, or top up
// to the max fuel capacity. Plane info can be updated via setters.


import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Plane implements Writable {
    private String type;
    private String callSign;
    private DayTime avails;
    private int hourlyRentalRate;
    private int hourlyFuelRate;
    private PlaneDocuments pd;
    private double fuelAmount;
    private double maxFuel;


    // EFFECT: creates an empty plane profile with plane type, call sign, availability for booking,
    // hourly rental and fuel rate, fuel amount in plane, and plane documents.
    public Plane() {
        type = "";
        callSign = "";
        avails = new DayTime();
        hourlyRentalRate = 0;
        hourlyFuelRate = 0;
        pd = new PlaneDocuments();
        fuelAmount = 0.0;
        maxFuel = 0.0;
    }

    // REQUIRES: 0 < amount <= maxFuel, and fuelAmount + amount <= maxFuel
    // MODIFIES: this
    // EFFECT: adds fuel to plane
    public void addFuel(double amount) {
        fuelAmount = amount + fuelAmount;
    }

    // MODIFIES: this
    // EFFECT: fuel up the aircraft to its maximum fuel capacity
    public void setFuelToMaxFuel() {
        fuelAmount = maxFuel;
    }

    public double getMaxFuel() {
        return maxFuel;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public DayTime getAvails() {
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

    public void setAvails(DayTime avails) {
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

    // EFFECT: returns plane written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("call sign", callSign);
        json.put("availability", avails.toJson());
        json.put("hourly rental rate", hourlyRentalRate);
        json.put("hourly fuel rate", hourlyFuelRate);
        json.put("plane doc", pd.toJson());
        json.put("fuel amount", fuelAmount);
        json.put("max fuel", maxFuel);

        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Plane plane = (Plane) o;
        return isPlaneEqual(plane);
    }

    private boolean isPlaneEqual(Plane plane) {
        boolean b = hourlyRentalRate == plane.hourlyRentalRate
                && hourlyFuelRate == plane.hourlyFuelRate
                && Double.compare(plane.fuelAmount, fuelAmount) == 0
                && Double.compare(plane.maxFuel, maxFuel) == 0
                && type.equals(plane.type)
                && callSign.equals(plane.callSign)
                && avails.equals(plane.avails)
                && pd.equals(plane.pd);

        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, callSign, avails, hourlyRentalRate, hourlyFuelRate, pd, fuelAmount, maxFuel);
    }
}
