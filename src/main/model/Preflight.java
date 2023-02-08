package model;

import java.util.ArrayList;

public class Preflight {

    private WeightBalance wb;
    private boolean isDocOnBoard;
    private boolean isCheckedFireExt;
    private boolean isWalkAroundDone;
    private boolean isFuelEnough;
    private boolean isWBDone;
    private boolean isPassengerBriefDone;
    private boolean isClearedTO;
    private boolean isInsuranceValid;


    // EFFECTS: create a preflight checklist ... (elaborate?)
    public Preflight() {
        wb = new WeightBalance();
        isDocOnBoard = false;
        isCheckedFireExt = false;
        isWalkAroundDone = false;
        isFuelEnough = false;
        isWBDone = false;
        isPassengerBriefDone = false;
        isClearedTO = false;
        isInsuranceValid = false;
    }

    public void setWb(WeightBalance wb) {
        this.wb = wb;
    }

    public void setCheckedFireExt(boolean checkedFireExt) {
        isCheckedFireExt = checkedFireExt;
    }

    public void setClearedTO(boolean clearedTO) {
        isClearedTO = clearedTO;
    }

    public void setDocOnBoard(boolean docOnBoard) {
        isDocOnBoard = docOnBoard;
    }

    public void setFuelEnough(boolean fuelEnough) {
        isFuelEnough = fuelEnough;
    }

    public void setPassengerBriefDone(boolean passengerBriefDone) {
        isPassengerBriefDone = passengerBriefDone;
    }

    public void setWalkAroundDone(boolean walkAroundDone) {
        isWalkAroundDone = walkAroundDone;
    }

    public void setWBDone(boolean wbDone) {
        isWBDone = wbDone;
    }

    public WeightBalance getWb() {
        return wb;
    }

    public void setInsuranceValid(boolean insuranceValid) {
        isInsuranceValid = insuranceValid;
    }

    public boolean isPassengerBriefDone() {
        return isPassengerBriefDone;
    }
}
