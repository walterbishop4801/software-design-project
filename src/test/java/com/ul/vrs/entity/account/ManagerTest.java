package com.ul.vrs.entity.account;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;
import com.ul.vrs.service.DamageCheckingService;
import com.ul.vrs.service.SalesReportService;
import com.ul.vrs.service.VehicleManagerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {
    private Manager manager;
    private VehicleManagerService vehicleManagerService;

    @BeforeEach
    public void setUp() {
        manager = new Manager("John Doe", "M001", "securePassword");
        vehicleManagerService = VehicleManagerService.getInstance();
        new DamageCheckingService();
        new SalesReportService(vehicleManagerService);
    }

    @Test
    public void testManagerCreation() {
        assertEquals("John Doe", manager.getUsername(), "Manager's name should match.");
        assertEquals("M001", manager.getAccountId(), "Manager's ID should match.");
        assertEquals("securePassword", manager.getPassword(), "Manager's password should match.");
    }

    @Test
    public void testAddVehicle() {
        Vehicle newVehicle = new Car(65165167, "Tesla Model S", "Tesla", 2023, 75_000, Color.BLUE, new PetrolFuel(), 5, 396);
        manager.addVehicle(vehicleManagerService, newVehicle);

        Optional<Vehicle> addedVehicle = vehicleManagerService.getVehicleById(newVehicle.getID());
        assertTrue(addedVehicle.isPresent(), "The new vehicle should be added to the inventory.");
        assertEquals("Tesla Model S", addedVehicle.get().getName(), "Vehicle name should match.");
        assertEquals("Tesla", addedVehicle.get().getBrandOwner(), "Vehicle manufacturer should match.");
        System.out.println("Vehicle name: " + addedVehicle.get().getName());
    }

    @Test
    public void testModifyVehicle() {
        // Arrange
        Vehicle existingVehicle = vehicleManagerService.getAllVehicles().get(0); // Get the first vehicle
        long newId = 999L; // New ID for the vehicle

        // Act
        existingVehicle.setID(newId); // Update the ID
        Optional<Vehicle> modifiedVehicle = vehicleManagerService.getVehicleById(newId);

        // Assert
        assertTrue(modifiedVehicle.isPresent(), "Vehicle with the updated ID should exist in the inventory.");
        assertEquals(newId, modifiedVehicle.get().getID(), "Updated ID should match.");
    }

    @Test
    public void testRemoveVehicle() {
        Vehicle existingVehicle = vehicleManagerService.getAllVehicles().get(0); // Get the first vehicle
        manager.removeVehicle(vehicleManagerService, existingVehicle);

        Optional<Vehicle> removedVehicle = vehicleManagerService.getVehicleById(existingVehicle.getID());
        assertFalse(removedVehicle.isPresent(), "The vehicle should be removed from the inventory.");
    }

    @Test
    public void testGenerateVehicleSalesReport() {
        String report = manager.generateVehicleSalesReport();
        assertNotNull(report, "The sales report should not be null.");
        assertTrue(report.contains("Vehicle Sales Report"), "The report should contain the header.");
    }

    @Test
    public void testAssignMechanicToVehicle() {
        Vehicle existingVehicle = vehicleManagerService.getAllVehicles().get(0); // Get the first vehicle
        manager.assignMechanicToVehicle(existingVehicle);

        // No state change happens in the current implementation; verify output manually if needed
        assertDoesNotThrow(() -> manager.assignMechanicToVehicle(existingVehicle), "Assigning mechanic should not throw an exception.");
    }
}