package com.ul.vrs.entity.account;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.service.DamageCheckingService;

public class Mechanic extends Account {

    public Mechanic(String name, String id, String password) {
        super(name, id, password);
    }

    /**
     * Service the vehicle
     * 
     * @param vehicle The vehicle to service
     */
    public void serviceVehicle(Vehicle vehicle) {
        System.out.println("Servicing vehicle: " + vehicle.getName() + " (ID: " + vehicle.getID() + ")");
        // Logic for servicing the vehicle 
        vehicle.updateState(VehicleState.IN_MAINTENANCE); // Vehicle goes to maintenance
    }

    /**
     * Fix the vehicle
     * 
     * @param vehicle The vehicle to fix
     */
    public void fixVehicle(Vehicle vehicle) {
        System.out.println("Fixing vehicle: " + vehicle.getName() + " (ID: " + vehicle.getID() + ")");
        // Logic for fixing the vehicle
        vehicle.updateState(VehicleState.AVAILABLE); // Vehicle becomes available after fixing
    }

    /**
     * View damage assessment report for a vehicle
     * 
     * @param vehicle The vehicle for which to view the report
     */
    public String viewDamageAssessmentReport(Vehicle vehicle) {
        System.out.println("Viewing damage assessment report for vehicle: " + vehicle.getName() + " (ID: " + vehicle.getID() + ")");
        // Include logic to fetch and display the damage assessment report
        String report = DamageCheckingService.generateReport();
        System.out.println(report);
        return report;
    }
}
