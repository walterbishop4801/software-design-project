package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;

import jakarta.persistence.*;

@Entity
public class InsuranceBookingDecorator extends BookingDecorator{
    public InsuranceBookingDecorator(Booking booking) {
        super(booking);
        this.decorators.add(Customization.INSURANCE);
    }

    public InsuranceBookingDecorator() {
        super();
        this.decorators.add(Customization.INSURANCE);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() + 100;
    }
}
