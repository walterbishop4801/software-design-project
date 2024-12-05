package com.ul.vrs.entity.booking.payment;

import com.ul.vrs.entity.booking.payment.strategy.ApplePayPaymentStrategy;
import com.ul.vrs.entity.booking.payment.strategy.CreditCardPaymentStrategy;
import com.ul.vrs.entity.booking.payment.strategy.PaymentStrategy;

public class PaymentRequest {
    private PaymentMethod method;
    private Payment payment;

    public PaymentRequest(PaymentMethod method, Payment payment) {
        this.method = method;
        this.payment = payment;
    }

    public PaymentStrategy getPaymentStrategy() {
        PaymentStrategy strategy = null;

        switch (method) {
            case CREDITCARD -> strategy = new CreditCardPaymentStrategy(payment);
            case APPLEPAY -> strategy = new ApplePayPaymentStrategy(payment);
            default -> System.err.println("Unkown payment strategy: " + method.name());
        }

        return strategy;
    }
}