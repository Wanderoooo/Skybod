package model;

// Represents a weight & balance calculation sheet with weight info of the aircraft, fuel (weight & US gallon info),
// pilot, passenger(s), and cargo. Also includes status of whether total weight is within takeoff
// limit. Weight info can be updated via setters.

import org.json.JSONObject;
import persistence.Writable;

public class WeightBalance implements Writable {
    private double aircraftWeight; // weights in lb
    private double fuelGallons;
    private double fuelWeight;
    private double pilotWeight;
    private double passengerWeight;
    private double takeoffWeight;
    private boolean isWithinLimit;

    // EFFECT: constructs empty weight and balance chart for calculation before flight,
    // including fuel, aircraft, passenger, and cargo weight info
    public WeightBalance() {
        aircraftWeight = 0.0;
        fuelGallons = 0.0;
        fuelWeight = 0.0;
        pilotWeight = 0.0;
        passengerWeight = 0.0;
        takeoffWeight = 0.0;
        isWithinLimit = false;
    }

    public double getAircraftWeight() {
        return aircraftWeight;
    }

    public double getFuelGallons() {
        return fuelGallons;
    }

    public double getFuelWeight() {
        return fuelWeight;
    }

    public double getPassengerWeight() {
        return passengerWeight;
    }

    public double getPilotWeight() {
        return pilotWeight;
    }

    public double getTakeoffWeight() {
        return takeoffWeight;
    }

    public void setAircraftWeight(double aircraftWeight) {
        this.aircraftWeight = aircraftWeight;
    }

    public void setFuelGallons(double fuelGallons) {
        this.fuelGallons = fuelGallons;
    }

    public void setFuelWeight(double fuelWeight) {
        this.fuelWeight = fuelWeight;
    }

    public void setPassengerWeight(double passengerWeight) {
        this.passengerWeight = passengerWeight;
    }

    public void setPilotWeight(double pilotWeight) {
        this.pilotWeight = pilotWeight;
    }

    public void setTakeoffWeight(double takeoffWeight) {
        this.takeoffWeight = takeoffWeight;
    }

    public void setWithinLimit(boolean withinLimit) {
        isWithinLimit = withinLimit;
    }

    public boolean isWithinLimit() {
        return isWithinLimit;
    }

    // EFFECT: returns weight & balance calculation written to JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("aircraft weight", aircraftWeight);
        json.put("fuel gallons", fuelGallons);
        json.put("fuel weight", fuelWeight);
        json.put("pilot weight", pilotWeight);
        json.put("passenger weight", passengerWeight);
        json.put("takeoff weight", takeoffWeight);
        json.put("within limit", isWithinLimit);

        return json;
    }
}
