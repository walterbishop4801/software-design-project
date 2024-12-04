package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;

public class VoucherBookingDecorator  extends BookingDecorator{
    public VoucherBookingDecorator(Booking booking) {
        super(booking);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() - 10;
    }
}
