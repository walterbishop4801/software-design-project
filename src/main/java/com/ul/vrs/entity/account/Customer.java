package com.ul.vrs.entity.account;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.RentalSystemService;
import com.ul.vrs.service.VehicleManagerService;

public class Customer extends Account {
	
	@Autowired
    private RentalSystemService rentalSystemService;

    @Autowired
    private VehicleManagerService vehicleManagerService;

    // Constructor
    public Customer(String username, String accountId, String password) {
        super(username, accountId, password);  // Call the superclass (Account) constructor
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

    public void authenticateBookingPayment(UUID booking) {
        rentalSystemService.authenticateBookingPayment(booking);
    }

    public void returnVehicle(UUID booking) {
        rentalSystemService.returnVehicle(booking);
    }

    public void cancelBooking(UUID vehicle) {
        rentalSystemService.cancelBooking(vehicle);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "username='" + getUsername() + '\'' +
                ", accountId='" + getAccountId() + '\'' +
                '}';
    }
}
