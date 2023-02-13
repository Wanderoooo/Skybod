package model;

public class PilotLog {
    private String day;
    private String time;
    private double flightTime;
    private String typeOfPiloting; // solo, dual
    private String planeType;
    private String planeCallSign;

    // EFFECT: create an empty record for pilot in command of flight; recording how long she/he flew,
    // type of piloting, and what plane she/he flew.
    public PilotLog() {
        day = null;
        time = null;
        flightTime = 0.0;
        typeOfPiloting = null;
        planeType = null;
        planeCallSign = null;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPlaneCallSign(String planeCallSign) {
        this.planeCallSign = planeCallSign;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public void setFlightTime(double flightTime) {
        this.flightTime = flightTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTypeOfPiloting(String typeOfPiloting) {
        this.typeOfPiloting = typeOfPiloting;
    }

    public String getPlaneCallSign() {
        return planeCallSign;
    }

    public String getPlaneType() {
        return planeType;
    }

    public double getFlightTime() {
        return flightTime;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public String getTypeOfPiloting() {
        return typeOfPiloting;
    }
}
