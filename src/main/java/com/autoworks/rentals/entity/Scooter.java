package com.autoworks.rentals.entity;

public class Scooter extends Vehicle {
    private boolean hasHelmetIncluded;
    private int maxPassengers;
    private int rangePerFuelTank;

    @Override
    public double getRentingCost() {
        // renting cost logic
        return 0;
    }
}