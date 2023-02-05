package model;

public class PilotLog {
    private String date;
    private String start;
    private String end;
    private String typeOfPiloting; // solo, dual
    private String planeType;
    private String planeCallSign;

    // EFFECT: create a record of the PIC of flight; recording how long she/he flew and
    // what plane she/he flew
    public PilotLog() {
        // stub
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

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
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

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public String getDate() {
        return date;
    }

    public String getTypeOfPiloting() {
        return typeOfPiloting;
    }
}
