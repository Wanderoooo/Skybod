package model;


import java.util.ArrayList;
import java.util.HashSet;

public class Pilot {
    private String name;
    private HashSet<String> ratings;
    private int medNum;
    private boolean isStudent;
    private ArrayList<Booking> bookings;
    private ArrayList<Booking> cancelled;
    private ArrayList<PilotLog> pl;

    // EFFECT: create a pilot profile with name, ratings, medical number, and
    // whether they are a student.
    public Pilot() {
        name = "";
        ratings = new HashSet<>();
        medNum = 0;
        isStudent = false;
        bookings = new ArrayList<>();
        pl = new ArrayList<>();
        cancelled = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds new flight data to pilot log
    public void updatePL() {
        // stub
    }

    // EFFECT:
    public void addRating(String r) {
        ratings.add(r);
    }

    // EFFECT:
    public void addBooking(Booking b) {
        bookings.add(b);
    }

    public ArrayList<PilotLog> getPl() {
        return pl;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<Booking> getCancelled() {
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

    public void setRatings(HashSet<String> ratings) {
        this.ratings = ratings;
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

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public void setCancelled(ArrayList<Booking> cancelled) {
        this.cancelled = cancelled;
    }

    public void setPl(ArrayList<PilotLog> pl) {
        this.pl = pl;
    }
}
