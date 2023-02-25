package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;

// Represents an availability schedule, including times when something is available on specific
// days of the week. Time availability can be added to a specific day individually, or by an
// interval provided boundaries. Time available on specific day can be removed individually.
// a specific day's timetable can be found provided the day, and also set to an updated timetable
// when drastic time change occurs.

public class DayTime implements Writable {
    private ArrayList<String> monday;
    private ArrayList<String> tuesday;
    private ArrayList<String> wednesday;
    private ArrayList<String> thursday;
    private ArrayList<String> friday;
    private ArrayList<String> saturday;
    private ArrayList<String> sunday;

    // EFFECT: constructs an availability timetable with no time availability added
    public DayTime() {
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();
        saturday = new ArrayList<>();
        sunday = new ArrayList<>();
    }


    // REQUIRES: d must be a day name in the week,
    // object not available on day d at time given
    // MODIFIES: this
    // EFFECT: make object available on day d at time
    public void addBackTimeGivenDay(String d, String time) {
        ArrayList<String> givenDay = findDay(d);
        givenDay.add(time);

        ArrayList<Integer> timeInInt = new ArrayList<>();

        for (String s : givenDay) {
            int intTime = Integer.parseInt(s);
            timeInInt.add(intTime);
        }

        Collections.sort(timeInInt);
        ArrayList<String> backToStringList = new ArrayList<>();

        for (Integer i : timeInInt) {
            String timeInString = Integer.toString(i);

            while (timeInString.length() < 4) {
                timeInString = "0" + timeInString;
            }

            backToStringList.add(timeInString);
        }

        setDay(d, backToStringList);
    }

    // REQUIRES: 0000 <= from < 2400, 0 <= to < 2400, from < to, from & to both are in exact hundreds,
    // d must be a day name in the week, object not already available any time at and between [from - to].
    // MODIFIES: this
    // EFFECT: 24h clock system, add availability on given day from: from - to, in hour increments
    public void addGivenDayTime(String d, String from, String to) {
        ArrayList<String> givenDay = findDay(d);
        int f = Integer.parseInt(from);
        int t = Integer.parseInt(to);

        for (int i = f; i < t; i += 100) {
            String time = Integer.toString(i);

            while (time.length() < 4) {
                time = "0" + time;
            }
            givenDay.add(time);
        }
    }

    // REQUIRE: d be a day name in the week
    // EFFECT: find time availability on day d and returns it
    public ArrayList<String> findDay(String d) {
        ArrayList foundDay = new ArrayList();
        switch (d.toUpperCase()) {
            case "MONDAY":
                return foundDay = monday;
            case "TUESDAY":
                return foundDay = tuesday;
            case "WEDNESDAY":
                return foundDay = wednesday;
            case "THURSDAY":
                return foundDay = thursday;
            case "FRIDAY":
                return foundDay = friday;
            case "SATURDAY":
                return foundDay = saturday;
            default:
                return foundDay = sunday;
        }

    }

    // REQUIRE: d be a day name, dayAvail is a list of time availability incrementing by 100s
    // MODIFIES: this
    // EFFECT: update the object's time availability on d to dayAvail
    public void setDay(String d, ArrayList<String> dayAvail) {
        switch (d.toUpperCase()) {
            case "MONDAY":
                monday = dayAvail;
                break;
            case "TUESDAY":
                tuesday = dayAvail;
                break;
            case "WEDNESDAY":
                wednesday = dayAvail;
                break;
            case "THURSDAY":
                thursday = dayAvail;
                break;
            case "FRIDAY":
                friday = dayAvail;
                break;
            case "SATURDAY":
                saturday = dayAvail;
                break;
            default:
                sunday = dayAvail;
        }

    }

    public void setTuesday(ArrayList<String> tuesday) {
        this.tuesday = tuesday;
    }

    public ArrayList<String> getFriday() {
        return friday;
    }

    public ArrayList<String> getMonday() {
        return monday;
    }

    public ArrayList<String> getSaturday() {
        return saturday;
    }

    public ArrayList<String> getSunday() {
        return sunday;
    }

    public ArrayList<String> getThursday() {
        return thursday;
    }

    public ArrayList<String> getTuesday() {
        return tuesday;
    }

    public ArrayList<String> getWednesday() {
        return wednesday;
    }

    // EFFECT: returns day time written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("monday", timesToJson(monday));
        json.put("tuesday", timesToJson(tuesday));
        json.put("wednesday", timesToJson(wednesday));
        json.put("thursday", timesToJson(thursday));
        json.put("friday", timesToJson(friday));
        json.put("saturday", timesToJson(saturday));
        json.put("sunday", timesToJson(sunday));

        return json;
    }

    // EFFECT: return times available in given day to a JSON array
    private JSONArray timesToJson(ArrayList<String> day) {
        JSONArray jsonArray = new JSONArray();

        for (String t : day) {
            jsonArray.put(t);
        }

        return jsonArray;
    }

}
