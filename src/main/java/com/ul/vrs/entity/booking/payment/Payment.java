package com.ul.vrs.entity.booking.payment;

public class Payment {
    private double amount = 10_000;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}