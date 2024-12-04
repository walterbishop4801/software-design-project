package com.ul.vrs.service;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.account.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final RentalSystemService rentalSystemService;
    private final VehicleManagerService vehicleManagerService;

    public AccountService(RentalSystemService rentalSystemService, VehicleManagerService vehicleManagerService) {
        this.rentalSystemService = rentalSystemService;
        this.vehicleManagerService = vehicleManagerService;
    }

    public List<Vehicle> searchAvailableVehicles() {
        return vehicleManagerService.getAllVehicles();
    }

    public void makeBooking(Customer customer, Vehicle vehicle) {
        rentalSystemService.makeBooking(customer, vehicle);
    }

    public void customizeVehicle(UUID booking) {
        rentalSystemService.customizeBooking(booking, null);
    }

    public void returnVehicle(UUID booking) {
        rentalSystemService.returnVehicle(booking);
    }

    public void cancelBooking(UUID booking) {
        rentalSystemService.cancelBooking(booking);
    }
}
