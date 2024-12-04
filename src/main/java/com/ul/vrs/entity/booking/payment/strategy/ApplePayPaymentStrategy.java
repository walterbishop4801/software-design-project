package com.ul.vrs.entity.booking.payment.strategy;

import com.ul.vrs.entity.booking.payment.Payment;

public class ApplePayPaymentStrategy extends PaymentStrategy {
    public ApplePayPaymentStrategy(Payment payment) {
        super(payment);
    }

    public boolean pay(double amount) {
        Payment payment = getPayment();

        if (payment.getAmount() >= amount) {
            payment.setAmount(payment.getAmount() - amount);
            return true;
        }

        return false;
    }
}