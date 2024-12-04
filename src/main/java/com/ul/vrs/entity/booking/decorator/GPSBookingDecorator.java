package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;

import jakarta.persistence.*;

@Entity
public class GPSBookingDecorator extends BookingDecorator {
    public GPSBookingDecorator(Booking booking) {
        super(booking);
        this.decorators.add(Customization.GPS);
    }

    public GPSBookingDecorator() {
        super();
        this.decorators.add(Customization.GPS);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() + 10;
    }
}