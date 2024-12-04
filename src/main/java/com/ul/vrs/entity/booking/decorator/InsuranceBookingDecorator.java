package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;

public class InsuranceBookingDecorator extends BookingDecorator{
    public InsuranceBookingDecorator(Booking booking) {
        super(booking);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() + 100;
    }
}
