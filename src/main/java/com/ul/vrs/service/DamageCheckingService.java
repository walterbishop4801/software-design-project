package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.SalesReportService.ReportUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class DamageCheckingService {

    // Map to simulate damage reports for vehicles
    private static Map<Long, String> damageReports = new HashMap<>();

    // Add a mock damage report for a vehicle
    public void addDamageReport(Long vehicleId, String damageDetails) {
        damageReports.put(vehicleId, damageDetails);
    }

    /**
     * Check for damage on a specific vehicle.
     *
     * @param vehicle Vehicle to check.
     * @return Damage report if damage exists, otherwise "No Damage Found".
     */
    public String checkForDamage(Vehicle vehicle) {
        Long vehicleId = vehicle.getID();
        if (damageReports.containsKey(vehicleId)) {
            String damageDetails = damageReports.get(vehicleId);
            System.out.println("Damage found for vehicle ID " + vehicleId + ": " + damageDetails);
            return "Damage Report: " + damageDetails;
        } else {
            System.out.println("No damage found for vehicle ID " + vehicleId);
            return "No Damage Found";
        }
    }

    /**
     * Apply a damage charge to the customer based on the severity of the damage.
     *
     * @param vehicle Vehicle that was damaged.
     * @param damageCost Cost of the damage to apply.
     * @return Confirmation of the applied charge.
     */
    public String applyChargeToCustomer(Vehicle vehicle, double damageCost) {
        Long vehicleId = vehicle.getID();
        String response = "Charge of $" + damageCost + " applied to vehicle ID " + vehicleId + ".";
        System.out.println(response);
        return response;
    }

    /**
     * Resolve a dispute about damage charges.
     *
     * @param vehicle Vehicle under dispute.
     * @return Confirmation of the dispute resolution.
     */
    public String resolveDispute(Vehicle vehicle) {
        Long vehicleId = vehicle.getID();
        if (damageReports.containsKey(vehicleId)) {
            System.out.println("Damage dispute resolved for vehicle ID " + vehicleId);
            return "Damage dispute resolved for vehicle ID " + vehicleId;
        } else {
            System.out.println("No damage report found for vehicle ID " + vehicleId + " to dispute.");
            return "No damage report found for vehicle ID " + vehicleId + " to dispute.";
        }
    }
    
    /**
     * Generate a comprehensive damage report for the manager.
     *
     * @return A string containing the damage report summary.
     * 
     */
    
    public static String generateDamageReport() {
        String header = "Comprehensive Damage Report:";
        return ReportUtility.buildReport(damageReports.entrySet(), header, entry -> 
            "Vehicle ID: " + entry.getKey() +
            ", Damage: " + entry.getValue()
        );
    }
}
