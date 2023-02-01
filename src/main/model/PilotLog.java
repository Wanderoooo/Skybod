package model;

public class PilotLog {
    private DateTime startPilot;
    private DateTime endPilot;
    private String typeOfPiloting; // solo, dual
    private String planeType;
    private String planeCallSign;

    // EFFECT: create a record of the PIC of flight; recording how long she/he flew and
    // what plane she/he flew
    public PilotLog() {
        // stub
    }

    public void setPlaneCallSign(String planeCallSign) {
        this.planeCallSign = planeCallSign;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public void setEndPilot(DateTime endPilot) {
        this.endPilot = endPilot;
    }

    public void setStartPilot(DateTime startPilot) {
        this.startPilot = startPilot;
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

    public DateTime getEndPilot() {
        return endPilot;
    }

    public DateTime getStartPilot() {
        return startPilot;
    }

    public String getTypeOfPiloting() {
        return typeOfPiloting;
    }
}
