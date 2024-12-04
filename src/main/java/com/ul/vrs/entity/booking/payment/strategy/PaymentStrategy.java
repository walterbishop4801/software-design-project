package com.ul.vrs.entity.booking.payment.strategy;

import com.ul.vrs.entity.booking.payment.Payment;

public abstract class PaymentStrategy {
    private Payment payment;

    public PaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public abstract boolean pay(double amount);
}