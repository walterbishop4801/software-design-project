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

            // Build the report
            StringBuilder report = new StringBuilder(header + "\n");
            for (T item : items) {
                report.append(formatter.format(item)).append("\n");
            }
            return report.toString();
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
