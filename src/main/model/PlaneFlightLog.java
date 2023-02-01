package model;

public class PlaneFlightLog {
    private DateTime flightTimeStart;
    private DateTime flightTimeEnd;
    private DateTime airTimeStart;
    private DateTime airTimeEnd;
    private double totalFT; // in hours
    private double totalAT; // in hours
    private String departingAP; // in code, ex. CZBB
    private String arrivingAP;

    // EFFECTS: create flight log with flight time, air time, and depart/arrival airport info
    public PlaneFlightLog() {
        // stub
    }
}
