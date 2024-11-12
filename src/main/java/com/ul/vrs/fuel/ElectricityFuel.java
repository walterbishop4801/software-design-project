package com.ul.vrs.fuel;

import com.ul.vrs.interfaces.Fuel;

public class ElectricityFuel implements Fuel {
    @Override
    public double getCost() {
        return 0;
    }
}