package model;

public class PlaneFlightLog {
    private double hobbsTimeStart;
    private double hobbsTimeEnd;
    private String departingAP; // in code, ex. CZBB
    private String arrivingAP;
    private double hoursTillMaint;


    // EFFECTS: create flight log with flight time, air time, and depart/arrival airport info
    public PlaneFlightLog() {
        hobbsTimeStart = 0.0;
        hobbsTimeEnd = 0.0;
        departingAP = null;
        arrivingAP = null;
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
}
