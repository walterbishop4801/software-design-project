package com.ul.vrs.entity.booking.payment;

public class PayUsingApplePay implements PayStrategy {

    private ApplePay wallet;

    public PayUsingApplePay(ApplePay wallet) {
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