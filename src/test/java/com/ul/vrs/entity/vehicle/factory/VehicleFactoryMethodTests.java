package com.ul.vrs.entity.vehicle.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Scooter;
import com.ul.vrs.entity.vehicle.Truck;
import com.ul.vrs.entity.vehicle.Van;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehicleFactoryMethodTests {
    private List<Object[]> testParams;

    private static final String[] TYPES_VEHICLES = new String[] { "Car", "Scooter", "Truck", "Van" };

    private static final List<Vehicle> EXPECTED_VEHICLES = new ArrayList<>(List.of(
        new Car(
            10000L, "Test_Name_Car", "Test_BrandOwner_Car", 2020, 150.5,
            Color.RED, new MockFuel(), VehicleState.AVAILABLE, 4, 250f
        ),
        new Scooter(
            10001L, "Test_Name_Scooter", "Test_BrandOwner_Scooter", 2020, 150.5,
            Color.RED, new MockFuel(), VehicleState.AVAILABLE, true, 2, 150
        ),
        new Truck(
            10002L, "Test_Name_Truck", "Test_BrandOwner_Truck", 2020, 150.5,
            Color.RED, new MockFuel(), VehicleState.AVAILABLE, 200f, 300f, 6
        ),
        new Van(
            10003L, "Test_Name_Van", "Test_BrandOwner_Van", 2020, 150.5,
            Color.RED, new MockFuel(), VehicleState.AVAILABLE, 250f, 4
        )
    ));

    // ------------------------
    // Mock classes
    // ------------------------

    private static class MockFuel implements Fuel {
        @Override
        public double getCost() {
            return 1.2;
        }
    }

    @BeforeAll
    public void setup() {
        testParams = new ArrayList<>();

        testParams.add(new Object[] {
            10000L, "Test_Name_Car", "Test_BrandOwner_Car", 2020, 150.5,
            Color.RED, EXPECTED_VEHICLES.get(0).getFuelType(), VehicleState.AVAILABLE, 4, 250f
        });

        testParams.add(new Object[] {
            10001L, "Test_Name_Scooter", "Test_BrandOwner_Scooter", 2020, 150.5,
            Color.RED, EXPECTED_VEHICLES.get(1).getFuelType(), VehicleState.AVAILABLE, true, 2, 150
        });

        testParams.add(new Object[] {
            10002L, "Test_Name_Truck", "Test_BrandOwner_Truck", 2020, 150.5,
            Color.RED, EXPECTED_VEHICLES.get(2).getFuelType(), VehicleState.AVAILABLE, 200f, 300f, 6
        });

        testParams.add(new Object[] {
            10003L, "Test_Name_Van", "Test_BrandOwner_Van", 2020, 150.5,
            Color.RED, EXPECTED_VEHICLES.get(3).getFuelType(), VehicleState.AVAILABLE, 250f, 4
        });
    }

    @Test
    public void testCreateVehicle() {
        for (int i = 0; i < TYPES_VEHICLES.length; i++) {
            String type = TYPES_VEHICLES[i];
            Object[] params = testParams.get(i);

            Vehicle expectedVehicle = EXPECTED_VEHICLES.get(i);
            Vehicle vehicle = VehicleFactoryMethod.createVehicle(type, params);
            assertEquals(expectedVehicle, vehicle);
        }
    }
}
