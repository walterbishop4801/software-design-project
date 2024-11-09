package com.autoworks.rentals.fuel;

import com.autoworks.rentals.interfaces.Fuel;

public class ElectricityFuel implements Fuel {
    @Override
    public double getCost() {
        return 0;
    }
}