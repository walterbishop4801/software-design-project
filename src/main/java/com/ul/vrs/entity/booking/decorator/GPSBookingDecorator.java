package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;

public class GPSBookingDecorator extends BookingDecorator {
    public GPSBookingDecorator(Booking booking) {
        super(booking);
    }

    // TODO: Adjust value based on real-life values
    public double getPrice() {
        return super.getPrice() + 10;
    }
}