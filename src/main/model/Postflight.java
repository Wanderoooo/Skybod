package model;

public class Postflight {
    private boolean isPlaneTiedDown;
    private double endHobbsTime;
    private boolean isDocComplete;

    // EFFECT: creates an empty postflight checklist with plane postflight status, ending hobbs time,
    // and document completion status
    public Postflight() {
        isPlaneTiedDown = false;
        isDocComplete = false;
        endHobbsTime = 0.0;
    }

    public void setEndHobbsTime(double endHobbsTime) {
        this.endHobbsTime = endHobbsTime;
    }

    public void setDocComplete(boolean docComplete) {
        isDocComplete = docComplete;
    }

    public void setPlaneTiedDown(boolean planeTiedDown) {
        isPlaneTiedDown = planeTiedDown;
    }

    public boolean getDocComplete() {
        return isDocComplete;
    }

    public double getEndHobbsTime() {
        return endHobbsTime;
    }

    public boolean getIsPlaneTiedDown() {
        return isPlaneTiedDown;
    }
}
