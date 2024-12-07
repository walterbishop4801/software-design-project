package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;
import java.util.List;

@Service
public class SalesReportService {
    private static VehicleManagerService vehicleManagerService;

    public SalesReportService(VehicleManagerService vehicleManagerService) {
        SalesReportService.vehicleManagerService = vehicleManagerService;
    }

    // Generate a sales report
    public static String generateReport() {
        List<Vehicle> vehicles = vehicleManagerService.getAllVehicles();
        StringBuilder report = new StringBuilder("Vehicle Sales Report:\n");

        for (Vehicle vehicle : vehicles) {
            report.append("ID: ").append(vehicle.getID())
                  .append(", Name: ").append(vehicle.getName())
                  .append(", Manufacturer: ").append(vehicle.getBrandOwner())
                  .append(", 1-day price: ").append(vehicle.getRentingCost(1))
                  .append("\n");
        }

        return report.toString();
    }
}