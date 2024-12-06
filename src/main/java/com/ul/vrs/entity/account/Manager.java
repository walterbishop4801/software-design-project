package com.ul.vrs.entity.account;

<<<<<<< Updated upstream
import java.util.Optional;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;
import com.ul.vrs.service.DamageCheckingService;
import com.ul.vrs.service.SalesReportService;
=======
>>>>>>> Stashed changes
import com.ul.vrs.service.VehicleManagerService;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.factory.VehicleFactoryMethod;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;

public class Manager {
    
    // Instance variable for the VehicleManagerService to manage vehicles
    private VehicleManagerService vehicleManager;

    // Constructor to inject VehicleManagerService
    public Manager(VehicleManagerService vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

<<<<<<< HEAD
    /**
<<<<<<< Updated upstream
     * Add a new vehicle to the inventory
     * 
     * @param service The vehicle management service
     * @param vehicle The new vehicle to add
     */
=======
>>>>>>> caa91544f9cfef2d5819628cc452b514e0e9c690
    public void addVehicle(VehicleManagerService service, Vehicle vehicle) {
        service.addVehicle(vehicle);
        System.out.println("Added new vehicle: " + vehicle.getName() + " (ID: " + vehicle.getID() + ")");
=======
     * Assign a mechanic to a vehicle.
     * @param mechanic the mechanic to assign
     * @param vehicle the vehicle to which the mechanic is assigned
     */
    public void assignMechanic(Mechanic mechanic, Vehicle vehicle) {
        if (vehicle != null && mechanic != null && vehicle.getState() instanceof AvailableVehicleState) {
            vehicle.updateState(new InMaintenanceVehicleState()); // Update state to 'IN_MAINTENANCE'
            System.out.println("Mechanic " + mechanic.getName() + " assigned to Vehicle ID: " + vehicle.getID());
        } else {
            System.out.println("Cannot assign mechanic. Vehicle is either null, unavailable, or already in maintenance.");
        }
>>>>>>> Stashed changes
    }

<<<<<<< HEAD
    /**
<<<<<<< Updated upstream
     * Modify or update details of an existing vehicle
     * 
     * @param service The vehicle management service
     * @param vehicle The vehicle with updated details
     */
=======
>>>>>>> caa91544f9cfef2d5819628cc452b514e0e9c690
    public void modifyVehicle(VehicleManagerService service, Vehicle updatedVehicle) {
        Optional<Vehicle> vehicleToModify = service.getVehicleById(updatedVehicle.getID());
=======
     * Liberate a mechanic from a vehicle.
     * @param mechanic the mechanic to release
     * @param vehicle the vehicle being maintained
     */
    public void liberateMechanic(Mechanic mechanic, Vehicle vehicle) {
        if (vehicle != null && mechanic != null && vehicle.getState() instanceof InMaintenanceVehicleState) {
            vehicle.updateState(new AvailableVehicleState()); // Update state to 'AVAILABLE'
            System.out.println("Mechanic " + mechanic.getName() + " liberated from Vehicle ID: " + vehicle.getID());
        } else {
            System.out.println("Cannot liberate mechanic. Vehicle is either null or not in maintenance.");
        }
    }
>>>>>>> Stashed changes

    /**
     * Add a new vehicle to the system using the VehicleFactoryMethod.
     * @param vehicleType the type of vehicle to add
     * @param params additional parameters needed to create the vehicle
     */
    public void addVehicle(String vehicleType, Object... params) {
        try {
            Vehicle newVehicle = VehicleFactoryMethod.createVehicle(vehicleType, params);
            if (newVehicle != null) {
                vehicleManager.addVehicle(newVehicle);
                System.out.println("New vehicle added: " + newVehicle);
            } else {
                System.out.println("Failed to add vehicle: Invalid vehicle type or parameters.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add vehicle: " + e.getMessage());
        }
    }

<<<<<<< HEAD
<<<<<<< Updated upstream


    /**
     * Remove a vehicle from the inventory
     * 
     * @param service The vehicle management service
     * @param vehicleId The ID of the vehicle to remove
     */
=======
>>>>>>> caa91544f9cfef2d5819628cc452b514e0e9c690
    public void removeVehicle(VehicleManagerService service, Vehicle vehicle) {
        service.deleteVehicle(vehicle.getID());
        System.out.println("Removed vehicle with ID: " + vehicle.getID());
    }

    public void assignMechanicToVehicle(VehicleManagerService service, Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = service.getVehicleById(vehicle.getID());

        if (optionalVehicle.isPresent()) {
            Vehicle foundVehicle = optionalVehicle.get();

            // Update the vehicle state to IN_MAINTENANCE
            foundVehicle.updateState(new InMaintenanceVehicleState());

            // Save the updated vehicle state using the service
            service.updateVehicle(foundVehicle.getID(), foundVehicle);

            // Log success message
            System.out.println("Assigned mechanic to vehicle: " + foundVehicle.getName() + " (ID: " + foundVehicle.getID() + ")");
=======
    /**
     * Update an existing vehicle's details.
     * @param vehicleId the ID of the vehicle to update
     * @param updatedVehicle the updated vehicle details
     */
    public void updateVehicle(Long vehicleId, Vehicle updatedVehicle) {
        if (vehicleId != null && updatedVehicle != null) {
            Vehicle existingVehicle = vehicleManager.getVehicleById(vehicleId).orElse(null);
            if (existingVehicle != null) {
                vehicleManager.updateVehicle(vehicleId, updatedVehicle);
                System.out.println("Vehicle ID: " + vehicleId + " updated successfully.");
            } else {
                System.out.println("Failed to update vehicle. Vehicle with ID: " + vehicleId + " does not exist.");
            }
>>>>>>> Stashed changes
        } else {
            System.out.println("Invalid input. Vehicle ID or updated vehicle cannot be null.");
        }
    }

<<<<<<< HEAD
<<<<<<< Updated upstream

    /**
     * Review customer feedback
     */
=======
>>>>>>> caa91544f9cfef2d5819628cc452b514e0e9c690
    public void reviewFeedback() {
        System.out.println("Reviewing customer feedback...");
        // Include logic to fetch and review feedback from a service or database
    }

    public String generateVehicleSalesReport() {
        System.out.println("Generating vehicle sales report...");

        // Include logic to generate and fetch the report from a service
        String report = SalesReportService.generateSalesReport();
        System.out.println(report);
        return report;
    }

    public String viewDamageAssessmentReport(Vehicle vehicle) {
        System.out.println("Viewing damage assessment report for vehicle: " + vehicle.getName() + " (ID: " + vehicle.getID() + ")");

        // Include logic to fetch and display the damage assessment report
        String report = DamageCheckingService.generateDamageReport();
        System.out.println(report);
        return report;
    }
}
=======
    /**
     * Remove a vehicle from the system.
     * @param vehicleId the ID of the vehicle to remove
     */
    public void removeVehicle(Long vehicleId) {
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
>>>>>>> Stashed changes
