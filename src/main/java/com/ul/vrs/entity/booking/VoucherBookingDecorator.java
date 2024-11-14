package com.ul.vrs.entity.booking;

public class VoucherBookingDecorator  extends BookingDecorator{
    // private long voucher_amount;

    public VoucherBookingDecorator(Booking booking){
        super(booking);
    }

    public long getPrice() {
        return super.getPrice() - 10;
    }
}
