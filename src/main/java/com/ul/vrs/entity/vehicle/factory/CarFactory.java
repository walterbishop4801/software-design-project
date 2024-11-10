package com.ul.vrs.entity.vehicle.factory;

class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car(0, null, null, 0, 0, null, null, 0, 0);
    }
}
