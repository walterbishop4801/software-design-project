package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;
import jakarta.persistence.*;

public class BookingDecorator extends Booking {
    @ManyToOne
    private final Booking booking;

    public BookingDecorator(Booking booking) {
        super(booking.getBookingId(), booking.getAccount(), booking.getVehicle(), booking.getNumberOfRentingDays());
        this.booking = booking;
    }

    public BookingDecorator() {
        Booking booking = new Booking();
        this.booking = booking;
    }

    public double getPrice() {
        return this.booking.getPrice();
    }
}