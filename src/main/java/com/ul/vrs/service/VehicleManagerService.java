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
public class VehicleManagerService {
    private List<Vehicle> vehicles = new ArrayList<>();
    private Long currentId = 3L;

    private static VehicleManagerService instance = getInstance();

    public static final VehicleManagerService getInstance() {
        if (instance == null) {
            instance = new VehicleManagerService();
        }

        return instance;
    }

    // TODO: Update this when database is available
    // TODO: Based on Singleton, this should be private, but for now we can leave it like that
    public VehicleManagerService() {
        vehicles.add(new Car(1L, "Camry", "Toyota", 2020,25_000, Color.WHITE, new PetrolFuel(), 4, 425));
        vehicles.add(new Car(2L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354));
        vehicles.add(new Car(3L, "Mustang", "Ford", 2021, 27_000, Color.RED, new PetrolFuel(), 2, 382));
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return vehicles.stream().filter(v -> v.getID() == id).findFirst();
    }

    // TODO: Include here database operations
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setID(currentId++);
        vehicles.add(vehicle);
        return vehicle;
    }

    // TODO: Include here database operations
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        deleteVehicle(id);
        return addVehicle(vehicleDetails);
    }

    // TODO: Include here database operations
    public void deleteVehicle(Long id) {
        vehicles.removeIf(v -> v.getID() == id);
    }
}
