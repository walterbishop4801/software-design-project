package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.account.Customer;

import java.util.UUID;

public class Booking {
    private final Customer customer;
    private final Vehicle vehicle;
    private final UUID bookingId;
    private final double price;
    private final int numberOfRentingDays;
    private boolean isAuthenticated;

    protected Booking(UUID bookingId, Customer customer, Vehicle vehicle, int numberOfRentingDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingId = bookingId;
        this.isAuthenticated = false;
        this.numberOfRentingDays = numberOfRentingDays;
        this.price = this.vehicle.getRentingCost(numberOfRentingDays);
    }

    public Booking(Customer customer, Vehicle vehicle, int numberOfRentingDays) {
        this(UUID.randomUUID(), customer, vehicle, numberOfRentingDays);
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public int getNumberOfRentingDays() {
        return this.numberOfRentingDays;
    }

    public double getPrice() {
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