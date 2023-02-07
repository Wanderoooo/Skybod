package model;

import java.util.ArrayList;
// how to represent int with leading 0

public class DayTime {
    private ArrayList<String> monday;
    private ArrayList<String> tuesday;
    private ArrayList<String> wednesday;
    private ArrayList<String> thursday;
    private ArrayList<String> friday;
    private ArrayList<String> saturday;
    private ArrayList<String> sunday;

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

    // REQUIRES: 0000 <= from <= 2400, 0 <= to <= 2400, from < to, from & to both are in exact hundreds,
    // d must be a day name: monday - sunday.
    // MODIFIES: this
    // EFFECT: 24h clock system, add availability on given day from: from - to, in hour increments
    public void addGivenDayTime(String d, String from, String to) {

        // add prevention of user from entering whatever
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

    // REQUIRES: d must be a day name: monday - sunday.
    // EFFECT: prints out availability on given day
    public void printDayAvail(String d) {
        System.out.print(d.substring(0, 1).toUpperCase() + d.substring(1).toLowerCase() + ": ");
        ArrayList<String> givenDay = findDay(d);
        for (String t : givenDay) {
            System.out.print(t + "   ");
        }
    }

    public ArrayList<String> findDay(String d) {
        ArrayList<String> foundDay;

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


    public void setFriday(ArrayList<String> friday) {
        this.friday = friday;
    }

    public void setMonday(ArrayList<String> monday) {
        this.monday = monday;
    }

    public void setSaturday(ArrayList<String> saturday) {
        this.saturday = saturday;
    }

    public void setSunday(ArrayList<String> sunday) {
        this.sunday = sunday;
    }

    public void setThursday(ArrayList<String> thursday) {
        this.thursday = thursday;
    }

    public void setTuesday(ArrayList<String> tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(ArrayList<String> wednesday) {
        this.wednesday = wednesday;
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
