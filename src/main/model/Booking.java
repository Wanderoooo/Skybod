package model;

// Represents a booking with plane info, instructor info, day & time of booking, preflight
// documentation (if complete), postflight documentation (if complete), reason cancelled (if cancelled)
// and type of lesson (if applicable). Booking info can be updated via the setters.

public class Booking {
    private Plane plane;
    private Instructor instructor;
    private String dayBooked;
    private String timeBooked;
    private Preflight pref;
    private Postflight postf;
    private String reasonCancelled;
    private String typeOfLesson;

    // EFFECT: make a booking with empty pilot, plane, and instructor info,
    // preflight & post-flight checklist, type of lesson if applicable,
    // and if it's cancelled along with the reason of cancellation.
    public Booking() {
        plane = null;
        instructor = null;
        dayBooked = null;
        timeBooked = null;
        pref = null;
        postf = null;
        reasonCancelled = null;
        typeOfLesson = null;

    }

    public Plane getPlane() {
        return plane;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Preflight getPref() {
        return pref;
    }

    public Postflight getPostf() {
        return postf;
    }

    public String getDayBooked() {
        return dayBooked;
    }

    public String getReasonCancelled() {
        return reasonCancelled;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public String getTypeOfLesson() {
        return typeOfLesson;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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

    public void setDayBooked(String dayBooked) {
        this.dayBooked = dayBooked;
    }

    public void setReasonCancelled(String reasonCancelled) {
        this.reasonCancelled = reasonCancelled;
    }

    public void setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
    }

    public void setTypeOfLesson(String typeOfLesson) {
        this.typeOfLesson = typeOfLesson;
    }

}
