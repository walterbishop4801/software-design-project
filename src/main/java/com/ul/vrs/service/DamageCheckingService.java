package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;
import java.util.HashMap;
import java.util.Map;

@Service
public class DamageCheckingService {
    private final Map<Long, String> damageReports = new HashMap<>();

    /**
     * Adds a damage report for a vehicle.
     *
     * @param vehicleId     The ID of the damaged vehicle.
     * @param damageDetails The details of the damage.
     */
    public void addDamageReport(Long vehicleId, String damageDetails) {
        damageReports.put(vehicleId, damageDetails);
        System.out.println("Damage report added for vehicle ID " + vehicleId);
    }

    /**
     * Checks for damage for a specific vehicle.
     *
     * @param vehicle The vehicle to check.
     * @return Damage report string.
     */
    public String checkForDamage(Vehicle vehicle) {
        Long vehicleId = vehicle.getID();
        String damageDetails = damageReports.get(vehicleId);

        if (damageDetails != null) {
            String response = "Damage found for vehicle ID " + vehicleId + ": " + damageDetails;
            System.out.println(response);
            return response;
        } else {
            String response = "No damage found for vehicle ID " + vehicleId;
            System.out.println(response);
            return response;
        }
    }

    /**
     * Applies a damage charge to the customer.
     *
     * @param vehicle     The damaged vehicle.
     * @param damageCost  The cost to apply.
     * @return Response string for the applied charge.
     */
    public String applyChargeToCustomer(Vehicle vehicle, double damageCost) {
        Long vehicleId = vehicle.getID();
        String response = "Charge of $" + damageCost + " applied to vehicle ID " + vehicleId + ".";
        System.out.println(response);
        return response;
    }

    /**
     * Resolves a damage dispute for a specific vehicle.
     *
     * @param vehicle The vehicle involved in the dispute.
     * @return Response string indicating the dispute resolution status.
     */
    public String resolveDispute(Vehicle vehicle) {
        Long vehicleId = vehicle.getID();
        if (damageReports.remove(vehicleId) != null) {
            String response = "Damage dispute resolved for vehicle ID " + vehicleId;
            System.out.println(response);
            return response;
        } else {
            String response = "No damage report found for vehicle ID " + vehicleId + " to dispute.";
            System.out.println(response);
            return response;
        }
    }

    /**
     * Generates a comprehensive damage report.
     *
     * @return The formatted damage report string.
     */
    public String generateDamageReport() {
        String header = "Comprehensive Damage Report:";
        return ReportUtility.buildReport(damageReports.entrySet(), header, entry ->
            "Vehicle ID: " + entry.getKey() +
            ", Damage: " + entry.getValue()
        );
    }
}
