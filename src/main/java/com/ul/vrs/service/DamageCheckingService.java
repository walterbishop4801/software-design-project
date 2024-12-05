package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.SalesReportService.ReportUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class DamageCheckingService {
    private static Map<Long, String> damageReports = new HashMap<>();

    public void addDamageReport(Long vehicleId, String damageDetails) {
        damageReports.put(vehicleId, damageDetails);
    }

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

    public String applyChargeToCustomer(Vehicle vehicle, double damageCost) {
        Long vehicleId = vehicle.getID();
        String response = "Charge of $" + damageCost + " applied to vehicle ID " + vehicleId + ".";
        System.out.println(response);
        return response;
    }

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
<<<<<<< HEAD
    
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
=======

    public static String generateReport() {
        if (damageReports.isEmpty()) {
            return "No damage reports available.";
        }

        StringBuilder report = new StringBuilder("Comprehensive Damage Report:\n");
        for (Map.Entry<Long, String> entry : damageReports.entrySet()) {
            report.append("Vehicle ID: ").append(entry.getKey())
                  .append(" - Damage: ").append(entry.getValue())
                  .append("\n");
        }

        return report.toString();
>>>>>>> 98bca6413625c5104a624831d3c39ed72763800f
    }
}