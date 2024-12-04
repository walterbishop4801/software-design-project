package com.ul.vrs.service;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.factory.VehicleFactoryMethod;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ul.vrs.repository.VehicleRepository;
@Service
public class VehicleManagerService {
    private final List<Vehicle> vehicles;
    private long currentId;

    @Autowired
    VehicleRepository vehicleRepository;

    private static VehicleManagerService instance = getInstance();

    public static final synchronized VehicleManagerService getInstance() {
        if (instance == null) {
            instance = new VehicleManagerService();
        }

        return instance;
    }

    // TODO: Update this when database is available
    // TODO: Based on Singleton, this should be private, but for now we can leave it like that
    public VehicleManagerService() {
        this.vehicles = new ArrayList<>();

        // addVehicle(new Car(1L, "Camry", "Toyota", 2020, 25_000, Color.WHITE, new PetrolFuel(), 4, 425));
        // addVehicle(new Car(2L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354));
        // addVehicle(new Car(3L, "Mustang", "Ford", 2021, 27_000, Color.RED, new PetrolFuel(), 2, 382));

        this.currentId = this.vehicles.size();
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findById(id);
    }

    // TODO: Include here database operations
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);

        return vehicle;
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {

        vehicleDetails.setID(id);
        vehicleRepository.save(vehicleDetails);

        return vehicleDetails;
    }

    // TODO: Include here database operations
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}