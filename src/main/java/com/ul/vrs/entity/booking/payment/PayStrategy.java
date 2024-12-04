package com.ul.vrs.entity.booking.payment;

public interface PayStrategy {
    boolean pay(long amount);
}