package com.ul.vrs.entity.booking.payment;

public class PayUsingCreditCard implements PayStrategy {

    private CreditCard card;

    public PayUsingCreditCard(CreditCard card) {
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