package com.ul.vrs.entity.booking.payment;

public class CreditCard extends Payment{
    private String cardNumber;
    private String expDate;
    private String cvv;

    public CreditCard(String cardNumber, String expDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
    }

    public String getCardNumber() { 
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCvv() {
        return cvv;
    }
}