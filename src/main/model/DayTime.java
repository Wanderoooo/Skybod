package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
// how to represent int with leading 0

public class DayTime {
    private ArrayList<String> monday;
    private ArrayList<String> tuesday;
    private ArrayList<String> wednesday;
    private ArrayList<String> thursday;
    private ArrayList<String> friday;
    private ArrayList<String> saturday;
    private ArrayList<String> sunday;
    ArrayList<String> foundDay;

    // EFFECT: constructs an availability timetable with no availability
    public DayTime() {
        monday = new ArrayList<>();
        tuesday = new ArrayList<>();
        wednesday = new ArrayList<>();
        thursday = new ArrayList<>();
        friday = new ArrayList<>();
        saturday = new ArrayList<>();
        sunday = new ArrayList<>();
    }

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

    // REQUIRES: 0000 <= from <= 2400, 0 <= to <= 2400, from <= to, from & to both are in exact hundreds,
    // d must be a day name: monday - sunday.
    // MODIFIES: this
    // EFFECT: 24h clock system, add availability on given day from: from - to, in hour increments
    public void addGivenDayTime(String d, String from, String to) {

        // add prevention of user from entering whatever
        ArrayList<String> givenDay = findDay(d);
        int f = Integer.parseInt(from);
        int t = Integer.parseInt(to);

        for (int i = f; i <= t; i += 100) {
            String time = Integer.toString(i);

            while (time.length() < 4) {
                time = "0" + time;
            }
            givenDay.add(time);
        }
    }

    public ArrayList<String> findDay(String d) {

        switch (d.toUpperCase()) {
            case "MONDAY":
                foundDay = monday;
                break;
            case "TUESDAY":
                foundDay = tuesday;
                break;
            case "WEDNESDAY":
                foundDay = wednesday;
                break;
            case "THURSDAY":
                foundDay = thursday;
                break;
            case "FRIDAY":
                foundDay = friday;
                break;
            case "SATURDAY":
                foundDay = saturday;
                break;
            default:
                foundDay = sunday;
        }

        return foundDay;
    }

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


}
