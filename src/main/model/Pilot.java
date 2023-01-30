package model;


import java.util.HashSet;

public class Pilot {
    private String name;
    private HashSet<String> ratings;
    private int medNum;
    private boolean isStudent;

    // EFFECT: create a pilot profile with name, ratings, medical number, and
    // whether they are a student.
    public Pilot() {
        // stub
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
}
