package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

// Represents a flight instructor with name, instructor class level (i.e. CFII), his/her
// obtained flight ratings, availability, hourly rate (in CAD$), and years of experience instructing.
// Instructor info can be updated via setters.

public class Instructor implements Writable {

    private String name;
    private String instrClass;
    private HashSet<String> ratings;
    private DayTime avails;
    private int hourlyRate;
    private int expYears;

    // EFFECT: create an instructor with empty name, instructor class, ratings,
    // availability, hourly rate, and years of experience.
    public Instructor() {
        name = "";
        instrClass = "";
        ratings = new HashSet<>();
        avails = new DayTime();
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

    // EFFECT: returns instructor written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("class", instrClass);
        json.put("ratings", ratingsToJson());
        json.put("availability", avails.toJson());
        json.put("hourly rate", hourlyRate);
        json.put("years of experience", expYears);

        return json;
    }

    // EFFECT: returns instructor ratings written to JSONArray object
    private JSONArray ratingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String r : ratings) {
            jsonArray.put(r);
        }

        return jsonArray;
    }

    // EFFECT: returns true if given object not null, class and every field of given object equals
    // to object which called this method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Instructor that = (Instructor) o;
        return isInstructorEqual(that);
    }

    // EFFECT: returns true if all fields of object are equivalent to given object
    private boolean isInstructorEqual(Instructor that) {
        boolean b = hourlyRate == that.hourlyRate
                && expYears == that.expYears
                && name.equals(that.name)
                && instrClass.equals(that.instrClass)
                && ratings.equals(that.ratings)
                && avails.equals(that.avails);

        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, instrClass, ratings, avails, hourlyRate, expYears);
    }
}
