package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.Fuel;


public interface VehicleFactory {
    Vehicle createVehicle(Object ... params);

}
