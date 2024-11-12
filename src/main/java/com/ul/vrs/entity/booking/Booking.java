package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.account.Customer;

import java.util.UUID;

public class Booking {
    private Customer customer;
    private Vehicle vehicle;
    private boolean is_authenticated;
    private UUID bookingId;
    private long price;

    /*public Booking() {
    }*/

    public Booking(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingId = UUID.randomUUID();
        this.is_authenticated = false;
        this.price = 10;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void authenticatePayment() {
        this.is_authenticated = true;
    }

    public long getPrice() {
        return this.price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean getIsAuthenticated() {
        return is_authenticated;
    }
}
