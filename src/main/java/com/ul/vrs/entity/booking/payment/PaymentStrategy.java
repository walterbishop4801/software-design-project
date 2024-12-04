package com.ul.vrs.entity.booking.payment;

public interface PaymentStrategy {
    boolean pay(long amount);
}