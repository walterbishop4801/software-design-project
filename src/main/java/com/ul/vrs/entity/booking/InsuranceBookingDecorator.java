package com.ul.vrs.entity.booking;

public class InsuranceBookingDecorator extends BookingDecorator{
    public InsuranceBookingDecorator(Booking booking) {
        super(booking);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() + 100;
    }
}
