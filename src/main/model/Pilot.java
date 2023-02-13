package model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Pilot {
    private String name;
    private HashSet<String> ratings;
    private int medNum;
    private boolean isStudent;
    private LinkedList<Booking> bookings;
    private LinkedList<Booking> toPostFlight;
    private LinkedList<Booking> completedBookings;
    private LinkedList<Booking> cancelled;
    private LinkedList<PilotLog> pl;

    // EFFECT: create an empty pilot profile with name, ratings, medical number,
    // bookings, bookings to postflight, completed bookings, cancelled bookings,
    // pilot logs, and whether they are a student.
    public Pilot() {
        name = null;
        ratings = new HashSet<>();
        medNum = 0;
        isStudent = false;
        bookings = new LinkedList<>();
        pl = new LinkedList<>();
        cancelled = new LinkedList<>();
        toPostFlight = new LinkedList<>();
        completedBookings = new LinkedList<>();
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

    public void setMedNum(int medNum) {
        this.medNum = medNum;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
