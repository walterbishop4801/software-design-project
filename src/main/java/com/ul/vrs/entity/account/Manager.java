package com.ul.vrs.entity.account;

import com.ul.vrs.service.VehicleManagerService;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.entity.vehicle.VehicleState;

public class Manager {
    private VehicleManagerService vehicleManager;

    public Manager(VehicleManagerService vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    // Assigns a mechanic to a vehicle and updates the vehicle's state
    public void assignMechanic(Mechanic m, Vehicle v) {
        if (v != null && m != null && v.checkAvailability()) {
            v.updateState(VehicleState.IN_MAINTENANCE); // Set state to 'IN_MAINTENANCE'
            System.out.println("Mechanic " + m.getName() + " assigned to Vehicle ID: " + v.getID());
        } else {
            System.out.println("Cannot assign mechanic. Vehicle is either null, unavailable, or already in maintenance.");
        }
    }

    // Releases a mechanic from a vehicle and updates the vehicle's state
    public void liberateMechanic(Mechanic m, Vehicle v) {
        if (v != null && m != null && v.getState() == VehicleState.IN_MAINTENANCE) {
            v.updateState(VehicleState.AVAILABLE); // Set state to 'AVAILABLE'
            System.out.println("Mechanic " + m.getName() + " liberated from Vehicle ID: " + v.getID());
        } else {
            System.out.println("Cannot liberate mechanic. Vehicle is either null or not in maintenance.");
        }
    }

    // Adds a new vehicle to the system
    public void add(String brandOwner, Color color, int releaseYear, Fuel fuel) {
        if (brandOwner != null && color != null && fuel != null) {
            Vehicle newVehicle = new Vehicle(vehicleManager.getAllVehicles().size() + 1, "New Vehicle", brandOwner, releaseYear, 0.0, color, fuel);
            vehicleManager.addVehicle(newVehicle);
            System.out.println("New vehicle added: " + newVehicle);
        } else {
            System.out.println("Failed to add vehicle. Invalid input.");
        }
    }

    // Updates an existing vehicle's details
    public void update(Long vehicleId, Vehicle updatedVehicle) {
        if (vehicleId != null && updatedVehicle != null) {
            Vehicle existingVehicle = vehicleManager.getVehicleById(vehicleId).orElse(null);
            if (existingVehicle != null) {
                vehicleManager.updateVehicle(vehicleId, updatedVehicle);
                System.out.println("Vehicle ID: " + vehicleId + " updated successfully.");
            } else {
                System.out.println("Failed to update vehicle. Vehicle with ID: " + vehicleId + " does not exist.");
            }
        } else {
            System.out.println("Invalid input. Vehicle ID or updated vehicle cannot be null.");
        }
    }

    // Removes a vehicle from the system
    public void remove(Long vehicleId) {
        if (vehicleId != null) {
            Vehicle existingVehicle = vehicleManager.getVehicleById(vehicleId).orElse(null);
            if (existingVehicle != null) {
                vehicleManager.deleteVehicle(vehicleId);
                System.out.println("Vehicle ID: " + vehicleId + " removed successfully.");
            } else {
                System.out.println("Failed to remove vehicle. Vehicle with ID: " + vehicleId + " does not exist.");
            }
        } else {
            System.out.println("Vehicle ID cannot be null.");
        }
    }
}
