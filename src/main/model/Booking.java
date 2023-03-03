package model;

// Represents a booking with plane info, instructor info, day & time of booking, preflight
// documentation (if complete), postflight documentation (if complete), reason cancelled (if cancelled)
// and type of lesson (if applicable). Booking info can be updated via the setters.

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Booking implements Writable {
    private Plane plane;
    private Instructor instructor;
    private String dayBooked;
    private String timeBooked;
    private Preflight pref;
    private String reasonCancelled;
    private String typeOfLesson;

    // EFFECT: make a booking with empty pilot, plane, and instructor info,
    // preflight & post-flight checklist, type of lesson if applicable,
    // and if it's cancelled along with the reason of cancellation.
    public Booking() {
        plane = new Plane();
        instructor = new Instructor();
        dayBooked = "";
        timeBooked = "";
        pref = new Preflight();
        reasonCancelled = "";
        typeOfLesson = "";

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

    // EFFECT: returns booking written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("plane", plane.toJson());
        json.put("instructor", instructor.toJson());
        json.put("day", dayBooked);
        json.put("time", timeBooked);
        json.put("preflight", pref.toJson());
        json.put("reason cancelled", reasonCancelled);
        json.put("type of lesson", typeOfLesson);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booking booking = (Booking) o;
        return plane.equals(booking.plane)
                && instructor.equals(booking.instructor)
                && dayBooked.equals(booking.dayBooked)
                && timeBooked.equals(booking.timeBooked)
                && pref.equals(booking.pref)
                && reasonCancelled.equals(booking.reasonCancelled)
                && typeOfLesson.equals(booking.typeOfLesson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plane, instructor, dayBooked, timeBooked, pref, reasonCancelled, typeOfLesson);
    }
}
