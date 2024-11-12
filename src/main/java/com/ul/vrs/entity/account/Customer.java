package com.ul.vrs.entity.account;

import com.ul.vrs.entity.account.Account.Active;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.BookingDecorator;
import com.ul.vrs.entity.booking.GPSBookingDecorator;
import com.ul.vrs.entity.booking.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.VoucherBookingDecorator;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private Account state;  // State Pattern for account status
    private List<Booking> bookings;  // Store customer's booking history

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.state = new Active();  // Default state
        this.bookings = new ArrayList<>();
    }

    // Getter and Setter methods
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Account getState() {
        return state;
    }

    public void setState(Account state) {
        this.state = state;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    // Add a new booking
    public void addBooking(Booking booking) {
        if (state instanceof Active) {  // Only allow booking if account is active
            bookings.add(booking);
            System.out.println("Booking added for customer: " + customerId);
        } else {
            System.out.println("Cannot add booking. Account is not active.");
        }
    }

    // Apply a GPS customization to the last booking
    public void addGPS() {
        if (!bookings.isEmpty()) {
            Booking lastBooking = bookings.get(bookings.size() - 1);
            BookingDecorator gpsBooking = new GPSBookingDecorator(lastBooking);
            bookings.set(bookings.size() - 1, gpsBooking);
            System.out.println("GPS added to booking for customer: " + customerId);
        }
    }

    // Apply an Insurance customization to the last booking
    public void addInsurance() {
        if (!bookings.isEmpty()) {
            Booking lastBooking = bookings.get(bookings.size() - 1);
            BookingDecorator insuranceBooking = new InsuranceBookingDecorator(lastBooking);
            bookings.set(bookings.size() - 1, insuranceBooking);
            System.out.println("Insurance added to booking for customer: " + customerId);
        }
    }

    // Apply a Voucher customization to the last booking
    public void addVoucher() {
        if (!bookings.isEmpty()) {
            Booking lastBooking = bookings.get(bookings.size() - 1);
            BookingDecorator voucherBooking = new VoucherBookingDecorator(lastBooking);
            bookings.set(bookings.size() - 1, voucherBooking);
            System.out.println("Voucher applied to booking for customer: " + customerId);
        }
    }

    // Show account status
    public void showAccountStatus() {
        state.handle(this);
    }
}
