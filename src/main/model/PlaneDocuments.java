package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.FlightPlanner;

import java.util.LinkedList;

// Represents plane documents with flight log, insurance info, and weight info. Plane document
// info can be updated via setters.

public class PlaneDocuments implements Writable {
    private LinkedList<PlaneFlightLog> fl;
    private Insurance insurance;
    private double weightInfo;

    // EFFECT: create an empty plane document profile with plane's flight log, insurance,
    // and weight info.
    public PlaneDocuments() {
        fl = new LinkedList<>();
        insurance = null;
        weightInfo = 0.0;
    }

    public LinkedList<PlaneFlightLog> getFl() {
        return fl;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public double getWeightInfo() {
        return weightInfo;
    }

    public void setFl(LinkedList<PlaneFlightLog> fl) {
        this.fl = fl;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setWeightInfo(double weightInfo) {
        this.weightInfo = weightInfo;
    }

    // EFFECT: returns booking written to JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("flight logs", flToJson());
        json.put("insurance", insurance.toJson());
        json.put("weight", weightInfo);

        return json;
    }

    // EFFECTS: returns flight logs in this plane document as a JSON array
    private JSONArray flToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PlaneFlightLog f : fl) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
