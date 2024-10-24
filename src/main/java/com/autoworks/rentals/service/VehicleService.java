package com.autoworks.rentals.service;

import com.autoworks.rentals.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    // List to store vehicles
    private List<Vehicle> vehicles = new ArrayList<>();
    
    // Current ID based on initialized list
    private Long currentId = 3L;

    // Constructor to initialize the list with some vehicles
    public VehicleService() {
    	vehicles.add(new Vehicle(1L, "Toyota", "Camry", "Reliable and fuel-efficient sedan", 2020, "White"));
    	vehicles.add(new Vehicle(2L, "Honda", "Civic", "Compact car with sporty performance", 2019, "Black"));
    	vehicles.add(new Vehicle(3L, "Ford", "Mustang", "Iconic muscle car with powerful engine", 2021, "Red"));

    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    // Get a vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicles.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    // Add a new vehicle to the list
    public Vehicle addVehicle(Vehicle vehicle) {
    	currentId++; // Increment the ID based on the current highest ID
        vehicle.setId(currentId); 
        vehicles.add(vehicle); 
        return vehicle;
    }

    // Update a vehicle by ID
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Optional<Vehicle> vehicleOptional = getVehicleById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setMake(vehicleDetails.getMake());
            vehicle.setModel(vehicleDetails.getModel());
            vehicle.setYear(vehicleDetails.getYear());
            vehicle.setColor(vehicleDetails.getColor());
            return vehicle;
        }
        return null;
    }

    // Delete a vehicle by ID
    public void deleteVehicle(Long id) {
        vehicles.removeIf(v -> v.getId().equals(id));
    }
}
