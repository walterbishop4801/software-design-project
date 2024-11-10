package com.ul.vrs.entity.vehicle;

class VanFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Van();
    }
}
