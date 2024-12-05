package com.ul.vrs.entity.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.repository.VehicleRepository;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.DamagedVehicleState;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;
import com.ul.vrs.entity.vehicle.state.ReservedVehicleState;
import com.ul.vrs.entity.vehicle.state.VehicleState;
import com.ul.vrs.service.VehicleManagerService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VanTests {
    private List<Van> testMockVehicles;
    private MockObserver testMockObserver;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleManagerService vehicleManagerService;

    private static final List<VehicleState> AVAILABLE_STATES = List.of(
        new AvailableVehicleState(), new DamagedVehicleState(), new InMaintenanceVehicleState(), new ReservedVehicleState()
    );

    private static final List<Map<String, Object>> EXPECTED_ATTRIBUTES = new ArrayList<>(List.of(
        Map.ofEntries(
            Map.entry("ID", 10000L),
            Map.entry("name", "Test_Name"),
            Map.entry("brandOwner", "Test_BrandOwner"),
            Map.entry("releaseYear", 2000),
            Map.entry("cost", 500.50),
            Map.entry("color", Color.BLACK),
            Map.entry("fuelType", new MockFuel()),
            Map.entry("state", new AvailableVehicleState()),
            Map.entry("rentingCost", 579.2),
            Map.entry("cargoCapacity", 250f),
            Map.entry("numberOfSeats", 4)
        )
    ));

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.testMockObserver = new MockObserver();
        initMockVehicle();

        for (Vehicle testMockVehicle : testMockVehicles) {
            vehicleManagerService.addVehicle(testMockVehicle);
        }
    }

    private void initMockVehicle() {
        this.testMockVehicles = new ArrayList<>();

        for (Map<String, Object> attrs : EXPECTED_ATTRIBUTES) {
            Long id = (Long) attrs.get("ID");
            String name = (String) attrs.get("name");
            String brandOwner = (String) attrs.get("brandOwner");
            Integer releaseYear = (Integer) attrs.get("releaseYear");
            Double cost = (Double) attrs.get("cost");
            Color color = (Color) attrs.get("color");
            Fuel fuelType = (Fuel) attrs.get("fuelType");
            VehicleState state = (VehicleState) attrs.get("state");
            Float cargoCapacity = (Float) attrs.get("cargoCapacity");
            Integer numberOfSeats = (Integer) attrs.get("numberOfSeats");

            this.testMockVehicles.add(new Van(
                id, name, brandOwner, releaseYear, cost, color,
                fuelType, state, cargoCapacity, numberOfSeats
            ));
        }
    }

    @Test
    public void testAddVehicle() {
        Van van = new Van(10001L, "New_Van", "New_Brand", 2022, 900.00, Color.BLUE, new MockFuel(), new AvailableVehicleState(), 500f, 8);
        when(vehicleRepository.save(van)).thenReturn(van);

        vehicleManagerService.addVehicle(van);

        verify(vehicleRepository, times(1)).save(van);
    }

    @Test
    public void testGetID() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("ID"), testMockVehicle.getID());
        }
    }

    @Test
    public void testSetID() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);

            testMockVehicle.setID(2L);
            assertEquals(2L, testMockVehicle.getID());
        }
    }

    @Test
    public void testGetName() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("name"), testMockVehicle.getName());
        }
    }

    @Test
    public void testGetBrandOwner() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("brandOwner"), testMockVehicle.getBrandOwner());
        }
    }

    @Test
    public void testGetReleaseYear() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("releaseYear"), testMockVehicle.getReleaseYear());
        }
    }

    @Test
    public void testGetColor() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("color"), testMockVehicle.getColor());
        }
    }

    @Test
    public void testGetFuelType() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("fuelType"), testMockVehicle.getFuelType());
        }
    }

    @Test
    public void testGetRentingCost() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("rentingCost"), testMockVehicle.getRentingCost(1));
        }
    }

    @Test
    public void testGetCargoCapacity() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Van testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("cargoCapacity"), testMockVehicle.getCargoCapacity());
        }
    }

    @Test
    public void testGetNumberOfSeats() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Van testMockVehicle = testMockVehicles.get(i);
            Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

            assertEquals(attrs.get("numberOfSeats"), testMockVehicle.getNumberOfSeats());
        }
    }

    @Test
    public void testUpdateState() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            for (VehicleState state : AVAILABLE_STATES) {
                Vehicle testMockVehicle = testMockVehicles.get(i);
                Map<String, Object> attrs = EXPECTED_ATTRIBUTES.get(i);

                final long ID = (Long) attrs.get("ID");

                testMockVehicle.updateState(state);
                assertEquals(state, testMockVehicle.getState());

                Optional<Vehicle> updatedVehicle = vehicleManagerService.getVehicleById(ID);

                assertTrue(updatedVehicle.isPresent());
                assertEquals(testMockVehicle, updatedVehicle.get());
            }
        }
    }

    @Test
    public void testNotifyObservers() {
        for (int i = 0; i < testMockVehicles.size(); i++) {
            Vehicle testMockVehicle = testMockVehicles.get(i);

            testMockVehicle.attach(testMockObserver);
            testMockVehicle.notifyObservers();

            assertTrue(testMockObserver.signalReceived);
        }
    }

    private static class MockFuel implements Fuel {
        @Override
        public double getCost() {
            return 1.2;
        }
    }

    private class MockObserver implements Observer {
        boolean signalReceived;

        @Override
        public void updateObserver(Subject subject) {
            signalReceived = true;
        }
    }
}