package com.ul.vrs.entity.vehicle.factory;

import org.springframework.stereotype.Component;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

@Component
public class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(Object... params) {
        return new Car(
            (long) params[0], (String) params[1], (String) params[2],
            (int) params[3], (double) params[4], (Color) params[5],
            (Fuel) params[6], (VehicleState) params[7],
            (int) params[8], (float) params[9]
        );
    }
}