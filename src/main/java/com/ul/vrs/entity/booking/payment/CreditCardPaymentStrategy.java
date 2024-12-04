package com.ul.vrs.entity.booking.payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {

    private CreditCardPayment card;

    public CreditCardPaymentStrategy(CreditCardPayment card) {
        this.card = card;
    }

    public boolean pay(long amount) {
        if (card.getAmount() >= amount) {
            card.setAmount(card.getAmount() - amount);
            return true;
        } else {
            return false;
        }

    }
}