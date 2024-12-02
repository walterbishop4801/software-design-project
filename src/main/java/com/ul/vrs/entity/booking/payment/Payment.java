package com.ul.vrs.entity.booking.payment;

public class Payment {
    protected long amount;

    public Payment() {
        this.amount = 10_000;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}
