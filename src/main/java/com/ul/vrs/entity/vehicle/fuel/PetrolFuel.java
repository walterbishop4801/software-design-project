package com.ul.vrs.entity.vehicle.fuel;

public class PetrolFuel implements Fuel {
    @Override
    public double getCost() {
        return 1.60;    // cost per litre
    }
}