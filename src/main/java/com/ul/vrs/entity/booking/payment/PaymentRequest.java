package com.ul.vrs.entity.booking.payment;

public class PaymentRequest {
    private PaymentMethod method;
    private CreditCardPayment card;
    private ApplePayPayment wallet;

    public PaymentRequest(PaymentMethod method, CreditCardPayment card, ApplePayPayment wallet) {
        this.method = method;
        this.card = card;
        this.wallet = wallet;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStrategy getPaymentStrategy() {
        switch (method) {
            case CREDITCARD:
                return new CreditCardPaymentStrategy(card);
            case APPLEPAY:
                return new ApplePayPaymentStrategy(wallet);
            default:
                return null;
        }
    }
}
