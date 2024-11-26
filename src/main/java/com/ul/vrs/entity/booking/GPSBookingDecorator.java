package com.ul.vrs.entity.booking;

public class GPSBookingDecorator extends BookingDecorator {
    public GPSBookingDecorator(Booking booking) {
        super(booking);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() + 10;
    }
}