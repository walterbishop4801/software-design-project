package com.ul.vrs.service;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

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
        vehicles.add(new Car(1L, "Camry", "Toyota", 2020,25_000, Color.WHITE, new PetrolFuel(), 4, 425));
        vehicles.add(new Car(2L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354));
        vehicles.add(new Car(3L, "Mustang", "Ford", 2021, 27_000, Color.RED, new PetrolFuel(), 2, 382));
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    // Get a vehicle by ID
    public Optional<Vehicle> getVehicleById(long id) {
        return vehicles.stream().filter(v -> v.getID() == id).findFirst();
    }

    // Add a new vehicle to the list
    public Vehicle addVehicle(Vehicle vehicle) {
        currentId++; // Increment the ID based on the current highest ID
        vehicle.setID(currentId);
        vehicles.add(vehicle);
        return vehicle;
    }

    // Update a vehicle by ID
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        deleteVehicle(id);
        return addVehicle(vehicleDetails);
    }

    // Delete a vehicle by ID
    public void deleteVehicle(Long id) {
        vehicles.removeIf(v -> v.getID() == id);
    }
}
