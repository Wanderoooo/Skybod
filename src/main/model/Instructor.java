package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Instructor {

    private String name;
    private String instrClass;
    private HashSet<String> ratings;
    private ArrayList<DateTime> avails;
    private int hourlyRate;
    private int expYears;

    // EFFECT: create an instructor with name, instructor class, ratings,
    // availability, hourly rate, and years of experience.
    public Instructor() {
        // stub
    }

    public ArrayList<DateTime> getAvails() {
        return avails;
    }

    public HashSet<String> getRatings() {
        return ratings;
    }

    public int getExpYears() {
        return expYears;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public String getInstrClass() {
        return instrClass;
    }

    public String getName() {
        return name;
    }

    public void setAvails(ArrayList<DateTime> avails) {
        this.avails = avails;
    }

    public void setExpYears(int expYears) {
        this.expYears = expYears;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setInstrClass(String instrClass) {
        this.instrClass = instrClass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRatings(HashSet<String> ratings) {
        this.ratings = ratings;
    }
}
