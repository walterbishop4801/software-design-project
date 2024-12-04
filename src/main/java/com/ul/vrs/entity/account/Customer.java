package com.ul.vrs.entity.account;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.RentalSystemService;
import com.ul.vrs.service.VehicleManagerService;

// TODO: This class doesn't look like an entity, as it has methods already defined at AccountController
public class Customer extends Account {
	@Autowired
    private RentalSystemService rentalSystemService;

    @Autowired
    private VehicleManagerService vehicleManagerService;

    public Customer(String username, String accountId, String password) {
        super(username, accountId, password);
    }

    public List<Vehicle> searchAvailableVehicles() {
        return vehicleManagerService.getAllVehicles();
    }

    public void makeBooking(Vehicle vehicle) {
        rentalSystemService.makeBooking(this, vehicle);
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