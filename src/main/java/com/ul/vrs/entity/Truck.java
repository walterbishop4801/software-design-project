package com.ul.vrs.entity;

public class Truck extends Vehicle {
    private float payloadCapacity;
    private float towingCapacity;
    private int numberOfAxles;

    @Override
    public double getRentingCost() {
        return 0;
    }
}