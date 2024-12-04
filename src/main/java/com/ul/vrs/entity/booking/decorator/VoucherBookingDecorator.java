package com.ul.vrs.entity.booking.decorator;

import com.ul.vrs.entity.booking.Booking;

import jakarta.persistence.*;

@Entity
public class VoucherBookingDecorator  extends BookingDecorator{
    public VoucherBookingDecorator(Booking booking) {
        super(booking);
        this.decorators.add(Customization.VOUCHER);
    }

    public VoucherBookingDecorator() {
        super();
        this.decorators.add(Customization.VOUCHER);
    }

    // TODO: Adjust value based on real-life values
    public long getPrice() {
        return super.getPrice() - 10;
    }
}
