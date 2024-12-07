package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

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
    }
}