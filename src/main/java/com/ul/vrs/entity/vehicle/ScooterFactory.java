package com.ul.vrs.entity.vehicle;

class ScooterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Scooter();
    }
}
