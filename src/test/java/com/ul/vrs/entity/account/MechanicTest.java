package com.ul.vrs.entity.account;

import org.junit.jupiter.api.Test;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;
import com.ul.vrs.service.DamageCheckingService;

import static org.junit.jupiter.api.Assertions.*;

class MechanicTest {

    @Test
    void testMechanicCreation() {
        // Arrange
        String expectedName = "Mechanic Mike";
        String expectedId = "MECH001";
        String expectedPassword = "securepass";

        // Act
        Mechanic mechanic = new Mechanic(expectedName, expectedId, expectedPassword);

        // Assert
        assertEquals(expectedName, mechanic.getUsername(), "Mechanic name should match.");
        assertEquals(expectedId, mechanic.getAccountId(), "Mechanic ID should match.");
        assertEquals(expectedPassword, mechanic.getPassword(), "Mechanic password should match.");
    }

    @Test
    void testServiceVehicle() {
        // Arrange
        Mechanic mechanic = new Mechanic("Mechanic Mike", "MECH001", "securepass");
        Vehicle vehicle = new Car(1L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354);

        // Act
        mechanic.serviceVehicle(vehicle);

        // Assert
        assertEquals(VehicleState.IN_MAINTENANCE, vehicle.getState(), "Vehicle state should be IN_MAINTENANCE after servicing.");
    }

    @Test
    void testFixVehicle() {
        // Arrange
        Mechanic mechanic = new Mechanic("Mechanic Mike", "MECH001", "securepass");
        Vehicle vehicle = new Car(1L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354);

        // Act
        mechanic.fixVehicle(vehicle);

        // Assert
        assertEquals(VehicleState.AVAILABLE, vehicle.getState(), "Vehicle state should be AVAILABLE after fixing.");
    }

    @Test
    void testViewDamageAssessmentReport() {
        // Arrange
        Mechanic mechanic = new Mechanic("Mechanic Mike", "MECH001", "securepass");
        Vehicle vehicle = new Car(1L, "Civic", "Honda", 2010, 8_000, Color.BLACK, new PetrolFuel(), 4, 354);
        // Mocking DamageCheckingService behavior (replace this logic with actual service mocks if needed)
        DamageCheckingService damageService = new DamageCheckingService();
        String generatedReport = damageService.generateReport();

        // Act
        String report = mechanic.viewDamageAssessmentReport(vehicle);

        // Assert
        assertNotNull(report, "Damage assessment report should not be null.");
        assertEquals(generatedReport, report, "Damage assessment report should match the generated report.");
    }
}
