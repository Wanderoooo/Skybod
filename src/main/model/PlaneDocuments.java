package model;

import java.util.ArrayList;

public class PlaneDocuments {
    private ArrayList<PlaneFlightLog> fl;
    private Insurance insurance;
    private double weightInfo;

    // EFFECT: create a plane profile with plane's flight log, insurance,
    // and weight info.
    public PlaneDocuments() {
        fl = new ArrayList<>();
        insurance = new Insurance();
        weightInfo = 0.0;
    }

    public ArrayList<PlaneFlightLog> getFl() {
        return fl;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public double getWeightInfo() {
        return weightInfo;
    }

    public void setFl(ArrayList<PlaneFlightLog> fl) {
        this.fl = fl;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setWeightInfo(double weightInfo) {
        this.weightInfo = weightInfo;
    }
}
