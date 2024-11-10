package com.ul.vrs.fuel;

import com.ul.vrs.nterfaces.Fuel;

public class ElectricityFuel implements Fuel {
    @Override
    public double getCost() {
        return 0;
    }
}