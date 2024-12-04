package com.ul.vrs.entity.booking.payment;

public class PaymentRequest {
    private PaymentMethod method;
    private CreditCard card;
    private ApplePay wallet;

    public PaymentRequest(PaymentMethod method, CreditCard card, ApplePay wallet) {
        this.method = method;
        this.card = card;
        this.wallet = wallet;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PayStrategy getPaymentStrategy() {
        switch (method) {
            case CREDITCARD:
                return new PayUsingCreditCard(card);
            case APPLEPAY:
                return new PayUsingApplePay(wallet);
            default:
                return null;
        }
    }
}
