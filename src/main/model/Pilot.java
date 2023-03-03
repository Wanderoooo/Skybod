package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

// Represents a pilot with name, ratings (i.e. Float, VFR, IFR, and/or Multi), medical number
// for fitness for flight, whether he/she is a student, all his/her flying & ground less bookings,
// all completed & cancelled bookings, bookings that needed to be postflighted, list of plane & instructors
// available for the pilot to book, weather info/record, and his/her pilot logs. New ratings & booking may be added,
// and other pilot info can be updated via setters.

public class Pilot implements Writable {
    private String name;
    private HashSet<String> ratings;
    private int medNum;
    private boolean isStudent;
    private LinkedList<Booking> bookings;
    private LinkedList<Booking> toPostFlight;
    private LinkedList<Booking> completedBookings;
    private LinkedList<Booking> cancelled;
    private LinkedList<PilotLog> pl;
    private ArrayList<Plane> lop;
    private ArrayList<Instructor> loi;
    private Weather wx;

    // EFFECT: create an empty pilot profile with name, ratings, medical number,
    // bookings, bookings to postflight, completed bookings, cancelled bookings,
    // pilot logs, list of instructor/planes available for booking, weather info/record,
    // and whether they are a student.
    public Pilot() {
        name = "";
        ratings = new HashSet<>();
        medNum = 0;
        isStudent = false;
        bookings = new LinkedList<>();
        pl = new LinkedList<>();
        cancelled = new LinkedList<>();
        toPostFlight = new LinkedList<>();
        completedBookings = new LinkedList<>();
        loi = new ArrayList<>();
        lop = new ArrayList<>();
        wx = new Weather();
    }

    // EFFECT: add given rating to pilot's ratings
    public void addRating(String r) {
        ratings.add(r);
    }

    // EFFECT: add given booking to pilot's bookings
    public void addBooking(Booking b) {
        bookings.add(b);
    }

    public LinkedList<Booking> getToPostFlight() {
        return toPostFlight;
    }

    public LinkedList<PilotLog> getPl() {
        return pl;
    }

    public LinkedList<Booking> getBookings() {
        return bookings;
    }

    public LinkedList<Booking> getCancelled() {
        return cancelled;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getRatings() {
        return ratings;
    }

    public int getMedNum() {
        return medNum;
    }

    public boolean getStudentStatus() {
        return isStudent;
    }

    public LinkedList<Booking> getCompletedBookings() {
        return completedBookings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompletedBookings(LinkedList<Booking> completedBookings) {
        this.completedBookings = completedBookings;
    }

    public void setToPostFlight(LinkedList<Booking> toPostFlight) {
        this.toPostFlight = toPostFlight;
    }

    public void setCancelled(LinkedList<Booking> cancelled) {
        this.cancelled = cancelled;
    }

    public void setBookings(LinkedList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setPl(LinkedList<PilotLog> pl) {
        this.pl = pl;
    }

    public void setRatings(HashSet<String> ratings) {
        this.ratings = ratings;
    }

    public void setMedNum(int medNum) {
        this.medNum = medNum;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }


    // EFFECT: returns pilot written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ratings", ratingsToJson());
        json.put("medical#", medNum);
        json.put("student", isStudent);
        json.put("bookings", bookingsToJson());
        json.put("to postflight", bookingsToPostflightToJson());
        json.put("completed bookings", completedBookingsToJson());
        json.put("cancelled bookings", cancelledBookingsToJson());
        json.put("pilot logs", plToJson());
        json.put("list of planes", lopToJson());
        json.put("list of instructors", loiToJson());
        json.put("wx", wx.toJson());
        return json;
    }

    // EFFECT: returns list of instructors available as a JSON array
    private JSONArray loiToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Instructor i : loi) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    // EFFECT: returns list of planes available as a JSON array
    private JSONArray lopToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Plane p : lop) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;

    }

    // EFFECT: returns pilot's pilot logs as a JSON array
    private JSONArray plToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PilotLog log : pl) {
            jsonArray.put(log.toJson());
        }

        return jsonArray;
    }

    // EFFECT: returns pilot's cancelled bookings as a JSON array
    private JSONArray cancelledBookingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Booking bk : cancelled) {
            jsonArray.put(bk.toJson());
        }

        return jsonArray;
    }

    // EFFECT: returns pilot's completed bookings as a JSON array
    private JSONArray completedBookingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Booking bk : completedBookings) {
            jsonArray.put(bk.toJson());
        }

        return jsonArray;
    }

    // EFFECT: returns pilot's bookings to postflight as a JSON array
    private JSONArray bookingsToPostflightToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Booking bk : toPostFlight) {
            jsonArray.put(bk.toJson());
        }

        return jsonArray;
    }

    // EFFECT: returns pilot's bookings as a JSON array
    private JSONArray bookingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Booking bk : bookings) {
            jsonArray.put(bk.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns pilot's ratings as a JSON array
    private JSONArray ratingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String r : ratings) {
            jsonArray.put(r);
        }

        return jsonArray;
    }

    public void setLoi(ArrayList<Instructor> loi) {
        this.loi = loi;
    }

    public void setLop(ArrayList<Plane> lop) {
        this.lop = lop;
    }

    public ArrayList<Instructor> getLoi() {
        return loi;
    }

    public ArrayList<Plane> getLop() {
        return lop;
    }

    public Weather getWx() {
        return wx;
    }

    public void setWx(Weather wx) {
        this.wx = wx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pilot pilot = (Pilot) o;
        return isPilotEqual(pilot);
    }

    private boolean isPilotEqual(Pilot pilot) {
        boolean b = medNum == pilot.medNum
                && isStudent == pilot.isStudent
                && name.equals(pilot.name)
                && ratings.equals(pilot.ratings)
                && bookings.equals(pilot.bookings)
                && toPostFlight.equals(pilot.toPostFlight)
                && completedBookings.equals(pilot.completedBookings)
                && cancelled.equals(pilot.cancelled)
                && pl.equals(pilot.pl)
                && lop.equals(pilot.lop)
                && loi.equals(pilot.loi)
                && wx.equals(pilot.wx);

        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ratings, medNum, isStudent, bookings, toPostFlight, completedBookings, cancelled,
                pl, lop, loi, wx);
    }
}
