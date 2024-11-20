package com.ul.vrs.entity.booking;

public class VoucherBookingDecorator  extends BookingDecorator{
    public VoucherBookingDecorator(Booking booking) {
        super(booking);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() - 10;
    }
}
