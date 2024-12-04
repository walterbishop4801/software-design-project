package com.ul.vrs.entity.vehicle.fuel;

public class DieselFuel implements Fuel {
    @Override
    public double getCost() {
        return 1.40;    // cost per litre
    }
}