package com.ul.vrs.entity.vehicle.fuel;

public class ElectricityFuel implements Fuel {
    @Override
    public double getCost() {
        return 0.22;    // cost per kWh
    }
}