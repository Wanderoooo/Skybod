package model;

// Represents a plane flight log, with record of engine start & stop time (hobbs time, in hours),
// 4-digit departing & arrival airport ICAO code, and hours left until aircraft is due for
// maintenance. Flight log info can be updated via setters.

import org.json.JSONObject;
import persistence.Writable;

public class PlaneFlightLog implements Writable {
    private double hobbsTimeStart;
    private double hobbsTimeEnd;
    private String departingAP; // in code, ex. CZBB
    private String arrivingAP;
    private double hoursTillMaint;


    // EFFECTS: creates an empty flight log with starting & ending hobbs time,
    // hours until aircraft maintenance, and departure/arrival airport info
    public PlaneFlightLog() {
        hobbsTimeStart = 0.0;
        hobbsTimeEnd = 0.0;
        departingAP = null;
        arrivingAP = null;
        hoursTillMaint = 0.0;
    }

    public String getDepartingAP() {
        return departingAP;
    }

    public String getArrivingAP() {
        return arrivingAP;
    }

    public double getHoursTillMaint() {
        return hoursTillMaint;
    }

    public double getHobbsTimeEnd() {
        return hobbsTimeEnd;
    }

    public double getHobbsTimeStart() {
        return hobbsTimeStart;
    }

    public void setDepartingAP(String departingAP) {
        this.departingAP = departingAP;
    }

    public void setArrivingAP(String arrivingAP) {
        this.arrivingAP = arrivingAP;
    }

    public void setHobbsTimeEnd(double hobbsTimeEnd) {
        this.hobbsTimeEnd = hobbsTimeEnd;
    }

    public void setHobbsTimeStart(double hobbsTimeStart) {
        this.hobbsTimeStart = hobbsTimeStart;
    }

    public void setHoursTillMaint(double hoursTillMaint) {
        this.hoursTillMaint = hoursTillMaint;
    }

    // EFFECT: returns plane flight log written to JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("hobbs start", hobbsTimeStart);
        json.put("hobbs end", hobbsTimeEnd);
        json.put("departing AP", departingAP);
        json.put("arriving AP", arrivingAP);
        json.put("hours til' maintenance", hoursTillMaint);

        return json;
    }
}
