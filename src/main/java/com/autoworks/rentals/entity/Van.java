package com.autoworks.rentals.entity;


public class Van extends Vehicle {
    private float cargoCapacity;
    private int numberOfSeats;

    @Override
    public double getRentingCost() {
        return 0;
    }
}
