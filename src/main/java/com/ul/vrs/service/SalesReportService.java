package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;
import java.util.Collection;
import java.util.List;

@Service
public class SalesReportService {
    private static VehicleManagerService vehicleManagerService;
    public SalesReportService(VehicleManagerService vehicleManagerService) {
        SalesReportService.vehicleManagerService = vehicleManagerService;
    }
    
    public class ReportUtility {

        // Generic report builder for any data type
        public static <T> String buildReport(Collection<T> items, String header, ReportFormatter<T> formatter) {
            // Check if the collection is empty
            if (items.isEmpty()) {
                return header + "No data available.\n";
            }

<<<<<<< HEAD
            // Build the report
            StringBuilder report = new StringBuilder(header + "\n");
            for (T item : items) {
                report.append(formatter.format(item)).append("\n");
            }
            return report.toString();
=======
        for (Vehicle vehicle : vehicles) {
            report.append("ID: ").append(vehicle.getID())
                  .append(", Name: ").append(vehicle.getName())
                  .append(", Manufacturer: ").append(vehicle.getBrandOwner())
                  .append(", 1-day price: ").append(vehicle.getRentingCost(1))
                  .append("\n");
>>>>>>> 98bca6413625c5104a624831d3c39ed72763800f
        }

        // Functional interface for custom formatting
        @FunctionalInterface
        public interface ReportFormatter<T> {
            String format(T item);
        }
    }

    
    public static String generateSalesReport() {
        List<Vehicle> vehicles = vehicleManagerService.getAllVehicles();
        String header = "Vehicle Sales Report:";
        return ReportUtility.buildReport(vehicles, header, vehicle -> 
            "Name: " + vehicle.getName() +
            ", Manufacturer: " + vehicle.getBrandOwner() +
            ", Price: " + vehicle.getRentingCost()
        );
    }

}
