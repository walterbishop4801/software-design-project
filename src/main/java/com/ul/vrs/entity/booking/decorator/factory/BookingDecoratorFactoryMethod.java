package com.ul.vrs.entity.booking.decorator.factory;

import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.GPSBookingDecorator;
import com.ul.vrs.entity.booking.decorator.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.decorator.VoucherBookingDecorator;

public class BookingDecoratorFactoryMethod {
    // Avoid instances with private constructor ...
    private BookingDecoratorFactoryMethod() { }

    public static Booking createBookingDecorator(Booking booking, Customization customizationType) {
        Booking bookingDecorator = booking;

        switch (customizationType) {
            case GPS -> bookingDecorator = new GPSBookingDecorator(booking);
            case INSURANCE -> bookingDecorator = new InsuranceBookingDecorator(booking);
            case VOUCHER -> bookingDecorator = new VoucherBookingDecorator(booking);
            default -> System.err.println("Unkown booking decorator: " + customizationType.name());
        }

        return bookingDecorator;
    }
}