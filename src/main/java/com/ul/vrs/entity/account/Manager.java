package com.ul.vrs.entity.account;

import com.ul.vrs.service.VehicleManagerService;
import com.ul.vrs.entity.vehicle.Vehicle;


public class Manager {
    private VehicleManagerService vehicleManager;

    public Manager(VehicleManagerService vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    public void assignMechanic(Mechanic m, Vehicle v) {
        // TODO: Implement method to assign a mechanic to a vehicle
    }

    public void liberateMechanic(Mechanic m, Vehicle v) {
        // TODO: Implement method to liberate a mechanic from a vehicle
    }

    public void addOwner(String owner, String color, int releaseYear, int fuel, FuelType fuelType) {
        // TODO: Implement method to add a new vehicle with owner and details
    }

    public void update(Vehicle v, Vehicle newVehicle) {
        // TODO: Implement method to update vehicle details
    }

    public void remove(Vehicle v) {
        // TODO: Implement method to remove a vehicle
    }
}
