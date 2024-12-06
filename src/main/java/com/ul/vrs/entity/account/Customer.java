package com.ul.vrs.entity.account;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.RentalSystemService;
import com.ul.vrs.service.VehicleManagerService;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
// TODO: This class doesn't look like an entity, as it has methods already defined at AccountController
public class Customer extends Account {
    @Transient
	@Autowired
    private RentalSystemService rentalSystemService;

    @Transient
    @Autowired
    private VehicleManagerService vehicleManagerService;

    public Customer(String username, String password) {
        super(username, password);
    }

    public Customer() {
        super("test_username", "test_password");
    }

    public List<Vehicle> searchAvailableVehicles() {
        return vehicleManagerService.getAllVehicles();
    }

    public void makeBooking(Vehicle vehicle, int numberOfRentingDays) {
        rentalSystemService.makeBooking(this.getUsername(), vehicle, numberOfRentingDays);
    }

    public void customizeVehicle(UUID booking) {
        rentalSystemService.customizeBooking(booking, null);
    }

    public void makeBookingPayment(UUID booking, PaymentRequest paymentRequest) {
        rentalSystemService.makeBookingPayment(booking, paymentRequest);
    }

    public void returnVehicle(UUID booking) {
        rentalSystemService.returnVehicle(booking);
    }

    public void cancelBooking(UUID vehicle) {
        rentalSystemService.cancelBooking(vehicle);
    }
}