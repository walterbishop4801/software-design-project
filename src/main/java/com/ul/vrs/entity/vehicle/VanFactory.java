package com.ul.vrs.entity;

class VanFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Van();
    }
}
