package model;


import java.util.ArrayList;
import java.util.HashSet;

public class Pilot {
    private String name;
    private HashSet<String> ratings;
    private int medNum;
    private boolean isStudent;
    private Booking booking;
    private ArrayList<PilotLog> pl;

    // EFFECT: create a pilot profile with name, ratings, medical number, and
    // whether they are a student.
    public Pilot() {
        name = "";
        ratings = new HashSet<>();
        medNum = 0;
        isStudent = false;
        booking = null;
        pl = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds new flight data to pilot log
    public void updatePL() {
        // stub
    }

    public void addRating(String r) {
        ratings.add(r);
    }

    public ArrayList<PilotLog> getPl() {
        return pl;
    }

    public Booking getBooking() {
        return booking;
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

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setPl(ArrayList<PilotLog> pl) {
        this.pl = pl;
    }
}
