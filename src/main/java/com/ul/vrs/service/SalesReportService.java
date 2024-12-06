package com.ul.vrs.service;

import org.springframework.stereotype.Service;
import com.ul.vrs.entity.vehicle.Vehicle;
import java.util.Collection;
import java.util.List;

@Service
public class SalesReportService {
    private final VehicleManagerService vehicleManagerService;

    public SalesReportService(VehicleManagerService vehicleManagerService) {
        this.vehicleManagerService = vehicleManagerService;
    }

    /**
     * Generates a sales report for all vehicles.
     *
     * @return Formatted sales report string.
     */
    public String generateSalesReport() {
        List<Vehicle> vehicles = vehicleManagerService.getAllVehicles();
        String header = "Vehicle Sales Report:";
        return ReportUtility.buildReport(vehicles, header, vehicle -> 
            "ID: " + vehicle.getID() +
            ", Name: " + vehicle.getName() +
            ", Manufacturer: " + vehicle.getBrandOwner() +
            ", 1-day Price: " + vehicle.getRentingCost(1)
        );
    }
}

/**
 * Utility class for building reports.
 */
class ReportUtility {
    /**
     * Builds a formatted report from a collection of items.
     *
     * @param <T> The type of items in the collection.
     * @param items The collection of items to report on.
     * @param header The header for the report.
     * @param formatter The formatter for each item.
     * @return The formatted report string.
     */
    public static <T> String buildReport(Collection<T> items, String header, ReportFormatter<T> formatter) {
        if (items.isEmpty()) {
            return header + "\nNo data available.\n";
        }

        StringBuilder report = new StringBuilder(header + "\n");
        for (T item : items) {
            report.append(formatter.format(item)).append("\n");
        }
        return report.toString();
    }
}

/**
 * Functional interface for formatting report items.
 *
 * @param <T> The type of the item.
 */
@FunctionalInterface
interface ReportFormatter<T> {
    String format(T item);
}
