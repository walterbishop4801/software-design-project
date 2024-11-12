package com.ul.vrs.entity.booking;

public class InsuranceBookingDecorator extends BookingDecorator{

    public InsuranceBookingDecorator(Booking booking){
        super(booking);
    }

    public long price() {
        return super.price() + 100;
    }
}
