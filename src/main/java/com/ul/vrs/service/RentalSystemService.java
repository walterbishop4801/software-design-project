package com.ul.vrs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ul.vrs.entity.Booking;
import com.ul.vrs.entity.Customer;
import com.ul.vrs.entity.vehicle.Vehicle;

/**
 * RentalSystemService
 *
 * @author David Parreño (losedavidpb)
 * @version 1.0.0
 */
@Service
public class RentalSystemService {
    private Customer customer;
    private List<Vehicle> availableVehicles;

    public RentalSystemService(Customer customer, List<Vehicle> availableVehicles) {
        this.customer = customer;
        this.availableVehicles = availableVehicles;
    }

    public List<Vehicle> getAvailableVehicles() {
        return availableVehicles;
    }

    public Booking makeBooking(Vehicle v) {
        return null;
    }

    public void customizeVehicle(Booking b) {

    }

    public void authenticateBookingPayment(Booking b) {

    }

    public void returnVehicle(Booking b) {

    }

    public void cancelBooking(Booking b) {

    }
}