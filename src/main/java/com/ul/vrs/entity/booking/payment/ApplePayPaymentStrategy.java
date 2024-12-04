package com.ul.vrs.entity.booking.payment;

public class ApplePayPaymentStrategy implements PaymentStrategy {

    private ApplePayPayment wallet;

    public ApplePayPaymentStrategy(ApplePayPayment wallet) {
        this.wallet = wallet;
    }

    public boolean pay(long amount) {
        if (wallet.getAmount() >= amount) {
            wallet.setAmount(wallet.getAmount() - amount);
            return true;
        } else {
            return false;
        }

    }
}