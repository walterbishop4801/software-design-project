package com.ul.vrs.entity.vehicle.factory;

import org.springframework.stereotype.Component;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Scooter;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.entity.vehicle.state.VehicleState;

@Component
public class ScooterFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle(Object... params) {
        return new Scooter(
            (long) params[0], (String) params[1], (String) params[2],
            (int) params[3], (double) params[4], (Color) params[5],
            (Fuel) params[6], (VehicleState) params[7], (boolean) params[8],
            (int) params[9], (int) params[10]
        );
    }
}