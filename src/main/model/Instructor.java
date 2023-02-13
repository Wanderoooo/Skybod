package model;

import java.util.HashSet;

public class Instructor {

    private String name;
    private String instrClass;
    private HashSet<String> ratings;
    private DayTime avails;
    private int hourlyRate;
    private int expYears;

    // EFFECT: create an instructor with empty name, instructor class, ratings,
    // availability, hourly rate, and years of experience.
    public Instructor() {
        name = null;
        instrClass = null;
        ratings = new HashSet<>();
        avails = null;
        hourlyRate = 0;
        expYears = 0;
    }

    public DayTime getAvails() {
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

    public void setAvails(DayTime avails) {
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
