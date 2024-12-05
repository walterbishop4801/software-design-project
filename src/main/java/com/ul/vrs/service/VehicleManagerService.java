package com.ul.vrs.service;

import com.ul.vrs.entity.vehicle.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.ul.vrs.repository.VehicleRepository;

@Service
public class VehicleManagerService {
    private final List<Vehicle> vehicles;

    @Autowired
    VehicleRepository vehicleRepository;

    private static VehicleManagerService instance;

    public static final synchronized VehicleManagerService getInstance() {
        if (instance == null) {
            instance = new VehicleManagerService();
        }

        return instance;
    }

    private VehicleManagerService() {
        this.vehicles = new ArrayList<>();

        //addVehicle(new Car(1L, "Camry", "Toyota", 2020, 25_000, Color.WHITE, new PetrolFuel(), 4, 425));
        //addVehicle(new Car(2L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354));
        //addVehicle(new Car(3L, "Mustang", "Ford", 2021, 27_000, Color.RED, new PetrolFuel(), 2, 382));
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        long vehicleID = vehicle.getID();

        // Update ID so it is unique
        if (getVehicleById(vehicleID).isPresent()) {
            vehicleID = generateID();
            vehicle.setID(vehicleID);
        }

        vehicles.add(vehicle);
        vehicleRepository.save(vehicle);

        System.out.println("Vehicle added with ID: " + vehicle.getID());
        return vehicle;
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        System.out.println("Attempting to update vehicle with ID: " + id);
        Optional<Vehicle> existingVehicle = getVehicleById(id);

        if (existingVehicle.isPresent()) {
            Vehicle existing = existingVehicle.get();
            int indexPrevious = vehicles.indexOf(existing);
            System.out.println("Updating vehicle at index: " + indexPrevious);
            vehicles.set(indexPrevious, vehicleDetails);
            vehicleDetails.setID(id);
            vehicleRepository.save(vehicleDetails);
        } else {
            System.out.println("Vehicle with ID " + id + " not found for update.");
            return null;
        }

        return vehicleDetails;
    }

    public void deleteVehicle(Long id) {
        vehicles.removeIf(v -> v.getID() == id);
        vehicleRepository.deleteById(id);
    }

    private long generateID() {
        Random random = new Random();
        Long randomID = 1L;

        while (getVehicleById(randomID).isPresent()) {
            randomID = Math.abs(random.nextLong());
        }

        return randomID;
    }
}