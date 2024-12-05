package com.ul.vrs.entity.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.DamagedVehicleState;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;
import com.ul.vrs.entity.vehicle.state.VehicleState;
import com.ul.vrs.service.VehicleManagerService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MechanicTests {
    private List<MockVehicle> testMockVehicles;
    private MockObserver testMockObserver;
    private Mechanic testMechanic;
    private VehicleManagerService vehicleManagerService;

    private static final List<Map<String, Object>> EXPECTED_ATTRIBUTES = new ArrayList<>(List.of(
        Map.ofEntries(
            Map.entry("ID", 10000L),
            Map.entry("name", "Test_Vehicle_One"),
            Map.entry("brandOwner", "Brand_One"),
            Map.entry("releaseYear", 2000),
            Map.entry("cost", 500.50),
            Map.entry("color", Color.BLACK),
            Map.entry("state", new AvailableVehicleState())
        ),
        Map.ofEntries(
            Map.entry("ID", 10001L),
            Map.entry("name", "Test_Vehicle_Two"),
            Map.entry("brandOwner", "Brand_Two"),
            Map.entry("releaseYear", 2010),
            Map.entry("cost", 1000.0),
            Map.entry("color", Color.RED),
            Map.entry("state", new AvailableVehicleState())
        )
    ));

    // ------------------------
    // Mock Classes
    // ------------------------

    private static class MockVehicle extends Vehicle {
        public MockVehicle(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, VehicleState vehicleState) {
            super(ID, name, brandOwner, releaseYear, cost, color, null, vehicleState);
        }

        @Override
        public double getRentingCost(int numberOfRentingDays) {
            return numberOfRentingDays * 50.0;
        }
    }

    private static class MockObserver implements Observer {
        boolean signalReceived = false;

        @Override
        public void updateObserver(Subject subject) {
            signalReceived = true;
        }
    }

    @BeforeAll
    public void setup() {
        this.testMechanic = new Mechanic("John Doe");
        this.testMockObserver = new MockObserver();
        this.vehicleManagerService = VehicleManagerService.getInstance();

        initMockVehicles();
        for (Vehicle vehicle : testMockVehicles) {
            vehicleManagerService.addVehicle(vehicle);
            System.out.println("Registered vehicle ID: " + vehicle.getID());
        }
    }

    @BeforeEach
    public void resetMocks() {
        initMockVehicles();
        vehicleManagerService.getAllVehicles().clear(); // Clear all vehicles before each test
        for (Vehicle vehicle : testMockVehicles) {
            vehicleManagerService.addVehicle(vehicle);
        }
    }

    private void initMockVehicles() {
        this.testMockVehicles = new ArrayList<>();

        for (Map<String, Object> attrs : EXPECTED_ATTRIBUTES) {
            Long id = (Long) attrs.get("ID");
            String name = (String) attrs.get("name");
            String brandOwner = (String) attrs.get("brandOwner");
            Integer releaseYear = (Integer) attrs.get("releaseYear");
            Double cost = (Double) attrs.get("cost");
            Color color = (Color) attrs.get("color");
            VehicleState state = (VehicleState) attrs.get("state");

            this.testMockVehicles.add(new MockVehicle(id, name, brandOwner, releaseYear, cost, color, state));
        }
    }

    // ------------------------
    // Test Methods
    // ------------------------

    @Test
    public void testAssignToVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing assign to vehicle with ID: " + vehicle.getID());

        testMechanic.assignToVehicle(vehicle);

        assertEquals(new InMaintenanceVehicleState(), vehicle.getState(), "Vehicle state should be IN_MAINTENANCE");
        Optional<Vehicle> retrievedVehicle = vehicleManagerService.getVehicleById(vehicle.getID());
        assertTrue(retrievedVehicle.isPresent(), "Vehicle should exist in the VehicleManagerService");
        System.out.println("Vehicle successfully assigned to mechanic. State: " + vehicle.getState());
    }

    @Test
    public void testReleaseFromVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing release from vehicle with ID: " + vehicle.getID());

        // Verify vehicle registration before assignment
        assertTrue(vehicleManagerService.getVehicleById(vehicle.getID()).isPresent(), "Vehicle must be registered before assignment.");

        // Assign mechanic to vehicle
        testMechanic.assignToVehicle(vehicle);
        System.out.println("Assigned mechanic to vehicle. Current state: " + vehicle.getState());

        // Verify vehicle registration after assignment
        assertTrue(vehicleManagerService.getVehicleById(vehicle.getID()).isPresent(), "Vehicle must still be registered after assignment.");

        // Release mechanic from vehicle
        testMechanic.releaseFromVehicle(vehicle);
        System.out.println("Released mechanic from vehicle. Current state: " + vehicle.getState());

        // Verify vehicle registration after release
        Optional<Vehicle> retrievedVehicle = vehicleManagerService.getVehicleById(vehicle.getID());
        System.out.println("Vehicle registration after release: " + retrievedVehicle.isPresent());

        // Assertions
        assertEquals(new AvailableVehicleState(), vehicle.getState(), "Vehicle state should be AVAILABLE.");
        assertTrue(retrievedVehicle.isPresent(), "Vehicle should still exist in VehicleManagerService.");
    }

    @Test
    public void testFixVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing fix vehicle with ID: " + vehicle.getID());

        vehicle.updateState(new DamagedVehicleState());
        testMechanic.fixVehicle(vehicle);

        assertEquals(new AvailableVehicleState(), vehicle.getState(), "Vehicle state should be AVAILABLE after fixing");
        System.out.println("Vehicle successfully fixed. State: " + vehicle.getState());
    }

    @Test
    public void testServiceVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing service vehicle with ID: " + vehicle.getID());

        testMechanic.assignToVehicle(vehicle);
        testMechanic.serviceVehicle(vehicle);

        assertEquals(new InMaintenanceVehicleState(), vehicle.getState(), "Vehicle state should remain IN_MAINTENANCE");
        System.out.println("Vehicle successfully serviced. State: " + vehicle.getState());
    }

    @Test
    public void testNotifyObservers() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing notify observers for vehicle with ID: " + vehicle.getID());

        vehicle.attach(testMockObserver);
        vehicle.updateState(new DamagedVehicleState());

        assertTrue(testMockObserver.signalReceived, "Observer should be notified of vehicle state change");
        System.out.println("Observer successfully notified of state change.");
    }
}