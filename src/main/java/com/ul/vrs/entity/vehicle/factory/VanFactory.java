package com.ul.vrs.entity.vehicle.factory;

class VanFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Van(0, null, null, 0, 0, null, null, 0, 0);
    }
}
