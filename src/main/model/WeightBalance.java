package model;

public class WeightBalance {
    private double aircraftWeight; // weights in lb
    private double fuelGallons;
    private double fuelWeight;
    private double pilotWeight;
    private double passengerWeight;
    private double takeoffWeight;
    private boolean isWithinLimit;

    // EFFECT: constructs weight and balance calculation before flight
    public WeightBalance() {
        // stub
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
}
