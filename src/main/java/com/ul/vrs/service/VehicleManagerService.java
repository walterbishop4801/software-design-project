package com.ul.vrs.service;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.factory.VehicleFactoryMethod;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleManagerService {
    private final List<Vehicle> vehicles;
    private long currentId;

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

        addVehicle(new Car(1L, "Camry", "Toyota", 2020, 25_000, Color.WHITE, new PetrolFuel(), 4, 425));
        addVehicle(new Car(2L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354));
        addVehicle(new Car(3L, "Mustang", "Ford", 2021, 27_000, Color.RED, new PetrolFuel(), 2, 382));

        this.currentId = this.vehicles.size();
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(this.vehicles);
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return vehicles.stream().filter(v -> v.getID() == id).findFirst();
    }

    // TODO: Include here database operations
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicle.getID() == 0L) {
            vehicle.setID(currentId);
        }

        vehicles.add(vehicle);
        this.currentId++;

        return vehicle;
    }

    // TODO: Include here database operations
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Optional<Vehicle> existingVehicle = getVehicleById(id);
        Vehicle updatedVehicle = null;

        if (existingVehicle.isPresent()) {
            Vehicle existing = existingVehicle.get();
            int indexPrevious = vehicles.indexOf(existing);

            updatedVehicle = VehicleFactoryMethod.createVehicle(vehicleDetails);
            this.vehicles.set(indexPrevious, updatedVehicle);
        }

        return updatedVehicle;
    }

    // TODO: Include here database operations
    public void deleteVehicle(Long id) {
        vehicles.removeIf(v -> v.getID() == id);
    }
}