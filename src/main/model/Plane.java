package model;

public class Plane {
    private String type;
    private String callSign;
    private DayTime avails;
    private int hourlyRentalRate;
    private int hourlyFuelRate;
    private PlaneDocuments pd;
    private double fuelAmount;
    private double maxFuel;


    // EFFECT: create a plane profile with empty plane type, call sign, availability for booking,
    // hourly rental and fuel rate, fuel amount in plane, and plane documents
    public Plane() {
        type = null;
        callSign = null;
        avails = null;
        hourlyRentalRate = 0;
        hourlyFuelRate = 0;
        pd = null;
        fuelAmount = 0.0;
        maxFuel = 0.0;
    }

    // REQUIRES: 0 < amount <= maxFuel, and fuelAmount + amount <= maxFuel
    public void addFuel(double amount) {
        fuelAmount = amount + fuelAmount;
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

    // REQUIRES:
    public void setFuelToMaxFuel() {
        fuelAmount = maxFuel;
    }
}
