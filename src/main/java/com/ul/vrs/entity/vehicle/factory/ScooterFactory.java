package com.ul.vrs.entity.vehicle.factory;

class ScooterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Scooter(0, null, null, 0, 0, null, null, false, 0, 0);
    }
}
