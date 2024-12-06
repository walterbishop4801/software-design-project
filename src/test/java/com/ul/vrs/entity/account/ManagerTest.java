package com.ul.vrs.entity.account;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;
import com.ul.vrs.service.DamageCheckingService;
import com.ul.vrs.service.SalesReportService;
import com.ul.vrs.service.VehicleManagerService;
import com.ul.vrs.repository.VehicleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManagerTest {
    @InjectMocks
    private VehicleManagerService vehicleManagerService;

    @Mock
    private VehicleRepository vehicleRepository;

    private Manager manager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        manager = new Manager("John Doe","securePassword");
        new DamageCheckingService();
        new SalesReportService(vehicleManagerService);
    }

    @Test
    public void testManagerCreation() {
        // Check if manager details are correctly initialized
        assertEquals("John Doe", manager.getUsername());
        assertEquals("securePassword", manager.getPassword());
    }

    @Test
    public void testAddVehicle() {
        // Create a new vehicle and mock repository save behavior
        Vehicle newVehicle = new Car(123L, "Tesla Model S", "Tesla", 2023, 75000, Color.BLUE, new PetrolFuel(), 5, 396);
        when(vehicleRepository.save(newVehicle)).thenReturn(newVehicle);

        manager.addVehicle(vehicleManagerService, newVehicle);

        // Assert: Verify interactions and that the vehicle is added
        verify(vehicleRepository, times(1)).save(newVehicle);
        when(vehicleRepository.findById(newVehicle.getID())).thenReturn(Optional.of(newVehicle));
        Optional<Vehicle> addedVehicle = vehicleManagerService.getVehicleById(newVehicle.getID());

        assertTrue(addedVehicle.isPresent());
        assertEquals("Tesla Model S", addedVehicle.get().getName());
        assertEquals("Tesla", addedVehicle.get().getBrandOwner());
    }

    @Test
    public void testRemoveVehicle() {
        // Mock an existing vehicle
        Vehicle existingVehicle = new Car(123L, "Model Y", "Tesla", 2021, 60000, Color.BLACK, new PetrolFuel(), 5, 400);
        when(vehicleRepository.findById(existingVehicle.getID())).thenReturn(Optional.of(existingVehicle));
        doNothing().when(vehicleRepository).deleteById(existingVehicle.getID());

        // Remove the vehicle
        manager.removeVehicle(vehicleManagerService, existingVehicle);

        // Assert: Verify delete interaction and that the vehicle is removed
        verify(vehicleRepository, times(1)).deleteById(existingVehicle.getID());
        when(vehicleRepository.findById(existingVehicle.getID())).thenReturn(Optional.empty());
        Optional<Vehicle> removedVehicle = vehicleManagerService.getVehicleById(existingVehicle.getID());

        assertFalse(removedVehicle.isPresent());
    }

    @Test
    public void testGenerateVehicleSalesReport() {
        // Arrange: Mock a vehicle and repository behavior
        Vehicle vehicle = new Car(123L, "Model 3", "Tesla", 2020, 50000, Color.RED, new PetrolFuel(), 4, 300);
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        // Act: Generate a sales report
        String report = manager.generateVehicleSalesReport();

        // Assert: Check report content
        assertNotNull(report);
        assertTrue(report.contains("Vehicle Sales Report"));
        assertTrue(report.contains("Model 3"));
    }

    @Test
    public void testAssignMechanicToVehicle() {
        // Arrange: Mock a vehicle and service interactions
        Vehicle existingVehicle = new Car(123L, "Cybertruck", "Tesla", 2024, 100000, Color.RED, new PetrolFuel(), 6, 1000);
        when(vehicleRepository.findById(existingVehicle.getID())).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(existingVehicle)).thenReturn(existingVehicle);
        when(vehicleManagerService.getVehicleById(existingVehicle.getID())).thenReturn(Optional.of(existingVehicle));

        // Act: Assign a mechanic to the vehicle
        manager.assignMechanicToVehicle(vehicleManagerService, existingVehicle);

        // Assert: Verify state update and repository interactions
        when(vehicleRepository.findById(existingVehicle.getID())).thenReturn(Optional.of(existingVehicle));
        verify(vehicleRepository, times(1)).save(existingVehicle);
        assertEquals(new InMaintenanceVehicleState(), existingVehicle.getState());
    }
}
