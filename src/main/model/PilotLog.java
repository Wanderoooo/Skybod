package model;

// Represents a pilot log, with day & time of flight, total flight time, type of piloting conducted
// (solo or dual), plane type flew and its call sign. Info can be updated via setters.

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class PilotLog implements Writable {
    private String day;
    private String time;
    private double flightTime;
    private String typeOfPiloting; // solo, dual
    private String planeType;
    private String planeCallSign;

    // EFFECT: create an empty record for pilot in command of flight; recording how long she/he flew,
    // type of piloting, and what plane she/he flew.
    public PilotLog() {
        day = "";
        time = "";
        flightTime = 0.0;
        typeOfPiloting = "";
        planeType = "";
        planeCallSign = "";
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

    // EFFECT: returns pilot log written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("time", time);
        json.put("flight time", flightTime);
        json.put("piloting type", typeOfPiloting);
        json.put("plane type", planeType);
        json.put("plane call sign", planeCallSign);

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
        PilotLog pilotLog = (PilotLog) o;
        return Double.compare(pilotLog.flightTime, flightTime) == 0 && day.equals(pilotLog.day)
                && time.equals(pilotLog.time) && typeOfPiloting.equals(pilotLog.typeOfPiloting)
                && planeType.equals(pilotLog.planeType) && planeCallSign.equals(pilotLog.planeCallSign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, time, flightTime, typeOfPiloting, planeType, planeCallSign);
    }
}
