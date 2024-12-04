package com.ul.vrs.entity.account;

import com.ul.vrs.service.VehicleManagerService;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.factory.VehicleFactoryMethod;
import com.ul.vrs.entity.Color;

import java.util.Objects;
import java.util.logging.Logger;

public class Manager {
    private static final Logger logger = Logger.getLogger(Manager.class.getName());
    private final VehicleManagerService vehicleManager;

    public Manager(VehicleManagerService vehicleManager) {
        this.vehicleManager = Objects.requireNonNull(vehicleManager, "VehicleManagerService cannot be null");
    }

    public void assignMechanic(Mechanic mechanic, Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle cannot be null");
        Objects.requireNonNull(mechanic, "Mechanic cannot be null");

        if (vehicle.getState() == VehicleState.AVAILABLE) {
            vehicle.updateState(VehicleState.IN_MAINTENANCE);
            logger.info("Mechanic " + mechanic.getName() + " assigned to Vehicle ID: " + vehicle.getID());
        } else {
            logger.warning("Cannot assign mechanic. Vehicle is unavailable or already in maintenance.");
        }
    }

    public void liberateMechanic(Mechanic mechanic, Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle cannot be null");
        Objects.requireNonNull(mechanic, "Mechanic cannot be null");

        if (vehicle.getState() == VehicleState.IN_MAINTENANCE) {
            vehicle.updateState(VehicleState.AVAILABLE);
            logger.info("Mechanic " + mechanic.getName() + " liberated from Vehicle ID: " + vehicle.getID());
        } else {
            logger.warning("Cannot liberate mechanic. Vehicle is not in maintenance.");
        }
    }

    public void add(String vehicleType, Object... params) {
        Objects.requireNonNull(vehicleType, "Vehicle type cannot be null");
        try {
            Vehicle newVehicle = VehicleFactoryMethod.createVehicle(vehicleType, params);
            if (newVehicle != null) {
                vehicleManager.addVehicle(newVehicle);
                logger.info("New vehicle added: " + newVehicle);
            } else {
                logger.warning("Failed to add vehicle: Invalid vehicle type or parameters.");
            }
        } catch (IllegalArgumentException e) {
            logger.severe("Failed to add vehicle: " + e.getMessage());
        }
    }

    public void update(Long vehicleId, Vehicle updatedVehicle) {
        Objects.requireNonNull(vehicleId, "Vehicle ID cannot be null");
        Objects.requireNonNull(updatedVehicle, "Updated vehicle cannot be null");

        vehicleManager.getVehicleById(vehicleId).ifPresentOrElse(
            existingVehicle -> {
                vehicleManager.updateVehicle(vehicleId, updatedVehicle);
                logger.info("Vehicle ID: " + vehicleId + " updated successfully.");
            },
            () -> logger.warning("Failed to update vehicle. Vehicle with ID: " + vehicleId + " does not exist.")
        );
    }

    public void remove(Long vehicleId) {
        Objects.requireNonNull(vehicleId, "Vehicle ID cannot be null");

        vehicleManager.getVehicleById(vehicleId).ifPresentOrElse(
            existingVehicle -> {
                vehicleManager.deleteVehicle(vehicleId);
                logger.info("Vehicle ID: " + vehicleId + " removed successfully.");
            },
            () -> logger.warning("Failed to remove vehicle. Vehicle with ID: " + vehicleId + " does not exist.")
        );
    }
}
