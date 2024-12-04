package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.account.Customer;

import java.util.UUID;

public class Booking {
    private final Customer customer;
    private final Vehicle vehicle;
    private final UUID bookingId;
    private final long price;
    private boolean isAuthenticated;

    protected Booking(UUID bookingId, Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingId = bookingId;
        this.isAuthenticated = false;

        // TODO: Adjust cost based on vehicle cost and renting duration
        this.price = 10;
    }

    public Booking(Customer customer, Vehicle vehicle) {
        this(UUID.randomUUID(), customer, vehicle);
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
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
        return isAuthenticated;
    }
}