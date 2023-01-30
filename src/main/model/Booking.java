package model;

public class Booking {
    private Pilot pilot;
    private Plane plane;
    private Instructor instructor;
    private Weather wx;
    private Preflight pref;
    private Postflight postf;
    private boolean isCancelled;
    private String reasonCancelled;

    // EFFECT: make a booking with pilot info, booking documentation,
    // most recent weather report, preflight & post-flight checklist,
    // and if it's cancelled along with the reason of cancellation.

    public Booking() {
        // stub
    }

    public Pilot getPilot() {
        return null;
    }

    public Plane getPlane() {
        return null;
    }

    public Instructor getInstructor() {
        return null;
    }

    public Weather getWx() {
        return null;
    }

    public Preflight getPref() {
        return null;
    }

    public Postflight getPostf() {
        return null;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setPostf(Postflight postf) {
        this.postf = postf;
    }

    public void setPref(Preflight pref) {
        this.pref = pref;
    }

    public void setWx(Weather wx) {
        this.wx = wx;
    }
}
