package model;

public class Insurance {
    private String dateValid;
    private String dateValidUntil;
    private int amountInsured;
    private String typeOfInsurance;
    private int price;
    private String planeCallSign;

    public Insurance(String planeCallSign) {
        dateValid = "";
        dateValidUntil = "";
        amountInsured = 0;
        typeOfInsurance = "";
        price = 0;
        this.planeCallSign = "";
    }

    public void setPlaneCallSign(String planeCallSign) {
        this.planeCallSign = planeCallSign;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTypeOfInsurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public String getPlaneCallSign() {
        return planeCallSign;
    }

    public int getAmountInsured() {
        return amountInsured;
    }

    public int getPrice() {
        return price;
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
