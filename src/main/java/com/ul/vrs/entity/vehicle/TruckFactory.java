package com.ul.vrs.entity.vehicle;

class TruckFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Truck(0, null, null, 0, 0, null, null, 0, 0, 0);
    }
}
