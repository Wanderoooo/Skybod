package model;

public class PilotLog {
    private String date;
    private String time;
    private double flightTime;
    private String typeOfPiloting; // solo, dual
    private String planeType;
    private String planeCallSign;

    // EFFECT: create a record of the PIC of flight; recording how long she/he flew and
    // what plane she/he flew
    public PilotLog() {
        date = "";
        time = "";
        flightTime = 0.0;
        typeOfPiloting = "";
        planeType = "";
        planeCallSign = "";
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public String getTypeOfPiloting() {
        return typeOfPiloting;
    }
}
