package model;

import java.util.ArrayList;

public class PlaneDocuments {
    private ArrayList<PlaneFlightLog> fl;
    private Insurance insurance;
    private WeightBalance wb;
    private PlaneReg pr;

    // EFFECT: create a plane profile with plane's flight log, insurance,
    // weight and balance, and registration
    public PlaneDocuments() {
        // stub
    }

    public ArrayList<PlaneFlightLog> getFl() {
        return fl;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public PlaneReg getPr() {
        return pr;
    }

    public WeightBalance getWb() {
        return wb;
    }

    public void setFl(ArrayList<PlaneFlightLog> fl) {
        this.fl = fl;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setPr(PlaneReg pr) {
        this.pr = pr;
    }

    public void setWb(WeightBalance wb) {
        this.wb = wb;
    }
}
