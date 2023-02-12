package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlaneDocuments {
    private LinkedList<PlaneFlightLog> fl;
    private Insurance insurance;
    private double weightInfo;

    // EFFECT: create a plane profile with plane's flight log, insurance,
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
}
