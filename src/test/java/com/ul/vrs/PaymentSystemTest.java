package com.ul.vrs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.ul.vrs.entity.booking.payment.ApplePayPayment;
import com.ul.vrs.entity.booking.payment.CreditCardPayment;
import com.ul.vrs.entity.booking.payment.PaymentMethod;
import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.booking.payment.PaymentStrategy;

class PaymentSystemTest {

    @Test
    public void testCreditCardPaymentSuccess() {
        CreditCardPayment card = new CreditCardPayment("1234", "12/25", "123");
        card.setAmount(10000);
        PaymentRequest request = new PaymentRequest(PaymentMethod.CREDITCARD, card, null);

        PaymentStrategy strategy = request.getPaymentStrategy();
        assertTrue(strategy.pay(5000));
        assertEquals(5000, card.getAmount());
    }

    @Test
    public void testCreditCardPaymentFailure() {
        CreditCardPayment card = new CreditCardPayment("1234", "12/25", "123");
        card.setAmount(5000);
        PaymentRequest request = new PaymentRequest(PaymentMethod.CREDITCARD, card, null);

        PaymentStrategy strategy = request.getPaymentStrategy();
        assertFalse(strategy.pay(10000));
        assertEquals(5000, card.getAmount());
    }

    @Test
    public void testApplePayPaymentSuccess() {
        ApplePayPayment wallet = new ApplePayPayment("user123", "authtoken");
        wallet.setAmount(15000);
        PaymentRequest request = new PaymentRequest(PaymentMethod.APPLEPAY, null, wallet);

        PaymentStrategy strategy = request.getPaymentStrategy();
        assertTrue(strategy.pay(10000));
        assertEquals(5000, wallet.getAmount());
    }

    @Test
    public void testApplePayPaymentFailure() {
        ApplePayPayment wallet = new ApplePayPayment("user123", "authtoken");
        wallet.setAmount(5000);
        PaymentRequest request = new PaymentRequest(PaymentMethod.APPLEPAY, null, wallet);

        PaymentStrategy strategy = request.getPaymentStrategy();
        assertFalse(strategy.pay(10000));
        assertEquals(5000, wallet.getAmount());
    }

}