package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.vehicle.Vehicle;

public interface VehicleFactory {
    Vehicle createVehicle(Object ... params);
}
