package com.ul.vrs.entity.booking;

public class GPSBookingDecorator extends BookingDecorator {

    public GPSBookingDecorator(Booking booking){
        super(booking);
    }

    public long price() {
        return super.price() + 10;
    }
    
}
