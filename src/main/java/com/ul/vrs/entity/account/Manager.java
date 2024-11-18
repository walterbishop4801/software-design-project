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

    // Assign a mechanic to a vehicle by updating its state to 'IN_MAINTENANCE'
    public void assignMechanic(Mechanic m, Vehicle v) {
        if (v != null && m != null && v.checkAvailability()) {
            v.updateState(VehicleState.IN_MAINTENANCE); // Mark vehicle as under maintenance
            System.out.println("Mechanic " + m.getName() + " assigned to Vehicle ID: " + v.getID());
        } else {
            System.out.println("Failed to assign Mechanic. Vehicle is either null, unavailable, or already in maintenance.");
        }
    }

    // Liberate a mechanic by changing vehicle state back to 'AVAILABLE'
    public void liberateMechanic(Mechanic m, Vehicle v) {
        if (v != null && m != null && v.getState() == VehicleState.IN_MAINTENANCE) {
            v.updateState(VehicleState.AVAILABLE); // Mark vehicle as available again
            System.out.println("Mechanic " + m.getName() + " liberated from Vehicle ID: " + v.getID());
        } else {
            System.out.println("Failed to liberate Mechanic. Vehicle is either null or not in maintenance.");
        }
    }

    // Add a new vehicle with the specified details
    public void add(String brandOwner, Color color, int releaseYear, Fuel fuel) {
        if (brandOwner != null && color != null && fuel != null) {
            Vehicle newVehicle = vehicleManager.createVehicle(brandOwner, color, releaseYear, fuel);
            vehicleManager.addVehicle(newVehicle); // Assume addVehicle method exists in VehicleManagerService
            System.out.println("New Vehicle added: " + newVehicle);
        } else {
            System.out.println("Failed to add Vehicle. Invalid inputs.");
        }
    }

    // Update an existing vehicle with new details
    public void update(Vehicle oldVehicle, Vehicle newVehicle) {
        if (oldVehicle != null && newVehicle != null) {
            boolean success = vehicleManager.updateVehicle(oldVehicle, newVehicle); // Assume updateVehicle method exists
            if (success) {
                System.out.println("Vehicle ID: " + oldVehicle.getID() + " updated successfully.");
            } else {
                System.out.println("Failed to update Vehicle. Vehicle might not exist.");
            }
        } else {
            System.out.println("Invalid Vehicles provided for update.");
        }
    }

    // Remove an existing vehicle
    public void remove(Vehicle v) {
        if (v != null) {
            boolean success = vehicleManager.removeVehicle(v); // Assume removeVehicle method exists
            if (success) {
                System.out.println("Vehicle ID: " + v.getID() + " removed successfully.");
            } else {
                System.out.println("Failed to remove Vehicle. Vehicle might not exist.");
            }
        } else {
            System.out.println("Vehicle cannot be null.");
        }
    }
}
