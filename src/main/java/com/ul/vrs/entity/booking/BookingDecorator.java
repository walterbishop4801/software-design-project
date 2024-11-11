package com.ul.vrs.entity.booking;

public class BookingDecorator extends Booking {
    private Booking booking;

    public BookingDecorator(Booking booking){
        this.booking = booking;
    }

    public long price() {
        return booking.price();
    }
}
