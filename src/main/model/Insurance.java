package model;

// Represents an aircraft insurance document with day valid - day of expiration
// (in format "mm/dd/yyyy"), amount insured (in CAD$), and type of insurance (i.e. Haul)
// Insurance document info can be updated via setters.

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Insurance implements Writable {
    private String dateValid;
    private String dateValidUntil;
    private int amountInsured;
    private String typeOfInsurance;

    // EFFECT: creates an empty insurance documentation with validity timeframe,
    // amount insured, and type of insurance.
    public Insurance() {
        dateValid = "";
        dateValidUntil = "";
        amountInsured = 0;
        typeOfInsurance = "";
    }

    public void setAmountInsured(int amountInsured) {
        this.amountInsured = amountInsured;
    }

    public void setDateValid(String dateValid) {
        this.dateValid = dateValid;
    }

    public void setDateValidUntil(String dateValidUntil) {
        this.dateValidUntil = dateValidUntil;
    }

    public void setTypeOfInsurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public int getAmountInsured() {
        return amountInsured;
    }

    public String getDateValid() {
        return dateValid;
    }

    public String getDateValidUntil() {
        return dateValidUntil;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    // EFFECT: returns insurance written to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("validity start", dateValid);
        json.put("validity end", dateValidUntil);
        json.put("amount", amountInsured);
        json.put("type", typeOfInsurance);
        return json;
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
        Insurance insurance = (Insurance) o;
        return isInsuranceEqual(insurance);
    }

    // EFFECT: returns true if all fields of object are equivalent to given object
    private boolean isInsuranceEqual(Insurance insurance) {
        boolean b = amountInsured == insurance.amountInsured
                && dateValid.equals(insurance.dateValid)
                && dateValidUntil.equals(insurance.dateValidUntil)
                && typeOfInsurance.equals(insurance.typeOfInsurance);

        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateValid, dateValidUntil, amountInsured, typeOfInsurance);
    }
}
