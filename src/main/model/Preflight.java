package model;

// Represents a preflight checklist with status of completion of weight & balance
// calculations, document check, fire extinguisher check, walkaround check, fuel check,
// passenger brief, insurance validity, hobbs time start, and departing airport code.
// Preflight checklist may be updated via setters.

import org.json.JSONObject;
import persistence.Writable;

public class Preflight implements Writable {

    private WeightBalance wb;
    private boolean isDocOnBoard;
    private boolean isCheckedFireExt;
    private boolean isWalkAroundDone;
    private boolean isFuelEnough;
    private boolean isWBDone;
    private boolean isPassengerBriefDone;
    private boolean isInsuranceValid;
    private double hobbsTimeStart;
    private String departAP;


    // EFFECTS: create an empty preflight checklist with status on plane and preflight items.
    public Preflight() {
        wb = null;
        isDocOnBoard = false;
        isCheckedFireExt = false;
        isWalkAroundDone = false;
        isFuelEnough = false;
        isWBDone = false;
        isPassengerBriefDone = false;
        isInsuranceValid = false;
        hobbsTimeStart = 0.0;
        departAP = null;
    }

    public void setWb(WeightBalance wb) {
        this.wb = wb;
    }

    public void setCheckedFireExt(boolean checkedFireExt) {
        isCheckedFireExt = checkedFireExt;
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

    public boolean isCheckedFireExt() {
        return isCheckedFireExt;
    }

    public boolean isDocOnBoard() {
        return isDocOnBoard;
    }

    public boolean isFuelEnough() {
        return isFuelEnough;
    }

    public boolean isInsuranceValid() {
        return isInsuranceValid;
    }

    public boolean isWalkAroundDone() {
        return isWalkAroundDone;
    }

    public boolean isWBDone() {
        return isWBDone;
    }

    public double getHobbsTimeStart() {
        return hobbsTimeStart;
    }

    public void setHobbsTimeStart(double hobbsTimeStart) {
        this.hobbsTimeStart = hobbsTimeStart;
    }

    public String getDepartAP() {
        return departAP;
    }

    public void setDepartAP(String departAP) {
        this.departAP = departAP;
    }

    // EFFECT: returns preflight document written to JSON object
    @Override
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("weight & balance", wb.toJson());
        json.put("doc onboard", isDocOnBoard);
        json.put("check fire extinguisher", isCheckedFireExt);
        json.put("walk around", isWalkAroundDone);
        json.put("fuel enough", isFuelEnough);
        json.put("w&b done", isWBDone);
        json.put("passenger brief", isPassengerBriefDone);
        json.put("insurance validity", isInsuranceValid);
        json.put("hobbs start", hobbsTimeStart);
        json.put("depart AP", departAP);

        return json;
    }
}
