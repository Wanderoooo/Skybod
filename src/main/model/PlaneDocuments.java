package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.FlightPlanner;

import java.util.LinkedList;
import java.util.Objects;

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
        insurance = new Insurance();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlaneDocuments that = (PlaneDocuments) o;
        return isPlaneDocEqual(that);
    }

    private boolean isPlaneDocEqual(PlaneDocuments that) {
        boolean b =  Double.compare(that.weightInfo, weightInfo) == 0
                && fl.equals(that.fl)
                && insurance.equals(that.insurance);

        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fl, insurance, weightInfo);
    }
}
