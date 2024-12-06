package com.ul.vrs.entity.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.DamagedVehicleState;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;
import com.ul.vrs.entity.vehicle.state.VehicleState;
import com.ul.vrs.repository.VehicleRepository;

@ExtendWith(MockitoExtension.class)
public class MechanicTests {
    private List<MockVehicle> testMockVehicles;
    private MockObserver testMockObserver;
    private Mechanic testMechanic;

    @Mock
    private VehicleRepository vehicleRepository;

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
        private VehicleState currentState;

        public MockVehicle(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, VehicleState vehicleState) {
            super(ID, name, brandOwner, releaseYear, cost, color, null, vehicleState);
            this.currentState = vehicleState;
        }

        @Override
        public double getRentingCost(int numberOfRentingDays) {
            return numberOfRentingDays * 50.0;
        }

        // Add methods to support Subject interface
        private List<Observer> observers = new ArrayList<>();

        public void attach(Observer observer) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }

        public void detach(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.updateObserver(this);
            }
        }

        public void updateState(VehicleState newState) {
            this.currentState = newState;
        }

        @Override
        public VehicleState getState() {
            return this.currentState;
        }
    }

    private static class MockObserver implements Observer {
        boolean signalReceived = false;

        @Override
        public void updateObserver(Subject subject) {
            signalReceived = true;
        }
    }

    @BeforeEach
    public void setup() {
        // Initialize test objects with a mock VehicleRepository
        this.testMechanic = new Mechanic("John Doe", vehicleRepository);
        this.testMockObserver = new MockObserver();
        initMockVehicles();
    }

    private void initMockVehicles() {
        this.testMockVehicles = new ArrayList<>();

        // Initialize vehicles with predefined attributes
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

        // Add necessary mock when needed
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        testMechanic.assignToVehicle(vehicle);

        // Verify state update and save interaction
        assertEquals(new InMaintenanceVehicleState(), vehicle.getState(), "Vehicle state should be IN_MAINTENANCE");
        verify(vehicleRepository, times(1)).save(vehicle);
        System.out.println("Vehicle successfully assigned to mechanic. State: " + vehicle.getState());
    }

    @Test
    public void testReleaseFromVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing release from vehicle with ID: " + vehicle.getID());

        // Add necessary mock when needed
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        // Assign mechanic to vehicle
        testMechanic.assignToVehicle(vehicle);

        // Release mechanic from vehicle
        testMechanic.releaseFromVehicle(vehicle);

        // Verify state update and save interaction
        assertEquals(new AvailableVehicleState(), vehicle.getState(), "Vehicle state should be AVAILABLE.");
        verify(vehicleRepository, times(2)).save(vehicle);
        System.out.println("Vehicle successfully released from mechanic. State: " + vehicle.getState());
    }

    @Test
    public void testFixVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing fix vehicle with ID: " + vehicle.getID());

        // Add necessary mock when needed
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        vehicle.updateState(new DamagedVehicleState());
        testMechanic.fixVehicle(vehicle);

        // Verify state update and save interaction
        assertEquals(new AvailableVehicleState(), vehicle.getState(), "Vehicle state should be AVAILABLE after fixing");
        verify(vehicleRepository, times(1)).save(vehicle);
        System.out.println("Vehicle successfully fixed. State: " + vehicle.getState());
    }

    @Test
    public void testServiceVehicle() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing service vehicle with ID: " + vehicle.getID());

        // Add necessary mock when needed
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        testMechanic.assignToVehicle(vehicle);
        testMechanic.serviceVehicle(vehicle);

        // Verify state remains IN_MAINTENANCE and save interaction
        assertEquals(new InMaintenanceVehicleState(), vehicle.getState(), "Vehicle state should remain IN_MAINTENANCE");
        verify(vehicleRepository, times(2)).save(vehicle);
        System.out.println("Vehicle successfully serviced. State: " + vehicle.getState());
    }

    @Test
    public void testNotifyObservers() {
        MockVehicle vehicle = testMockVehicles.get(0);
        System.out.println("Testing notify observers for vehicle with ID: " + vehicle.getID());

        vehicle.attach(testMockObserver);
        vehicle.updateState(new DamagedVehicleState());
        vehicle.notifyObservers();

        // Verify observer notification
        assertTrue(testMockObserver.signalReceived, "Observer should be notified of vehicle state change");
        System.out.println("Observer successfully notified of state change.");
    }
}
