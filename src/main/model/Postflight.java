package model;

public class Postflight {
    private boolean isPlaneTiedDown;
    private double endHobbsTime;

    // EFFECT:
    public Postflight() {
        isPlaneTiedDown = false;
        endHobbsTime = 0.0;
    }

    public void setEndHobbsTime(double endHobbsTime) {
        this.endHobbsTime = endHobbsTime;
    }

    public void setPlaneTiedDown(boolean planeTiedDown) {
        isPlaneTiedDown = planeTiedDown;
    }

    public double getEndHobbsTime() {
        return endHobbsTime;
    }

    public boolean getIsPlaneTiedDown() {
        return isPlaneTiedDown;
    }
}
