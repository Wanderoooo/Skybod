package model;

public class Insurance {
    private String dateValid;
    private String dateValidUntil;
    private int amountInsured;
    private String typeOfInsurance;

    public Insurance() {
        dateValid = null;
        dateValidUntil = null;
        amountInsured = 0;
        typeOfInsurance = null;
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

}
