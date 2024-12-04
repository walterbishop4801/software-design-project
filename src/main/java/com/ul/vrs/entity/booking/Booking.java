package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

import jakarta.persistence.*;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.booking.decorator.Customization;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "booking_type")
@DiscriminatorValue("BASE")
public class Booking {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private final Customer customer;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private final Vehicle vehicle;
    
    @Id
    private final UUID bookingId;
    private final double price;
    private final int numberOfRentingDays;
    private boolean isAuthenticated;

    @ElementCollection
    @CollectionTable(name = "booking_decorators", joinColumns = @JoinColumn(name = "booking_id"))
    protected List<Customization> decorators;

    protected Booking(UUID bookingId, Customer customer, Vehicle vehicle, int numberOfRentingDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.bookingId = bookingId;
        this.isAuthenticated = false;
        this.numberOfRentingDays = numberOfRentingDays;
        this.price = this.vehicle.getRentingCost(numberOfRentingDays);
        this.decorators = new ArrayList<>();
    }

    public Booking(Customer customer, Vehicle vehicle, int numberOfRentingDays) {
        this(UUID.randomUUID(), customer, vehicle, numberOfRentingDays);
    }

    public Booking() {
        this.customer = new Customer();
        this.vehicle = new Car();
        this.bookingId = UUID.randomUUID();
        this.isAuthenticated = false;
        this.price = 10;
        this.numberOfRentingDays = 1;
        this.decorators = new ArrayList<>();
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

    public List<Customization> getDecorators() {
        return this.decorators;
    }
}