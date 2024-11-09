package com.ul.vrs.entity;

import com.ul.vrs.enums.Color;

public class Car extends Vehicle {
    private int numberOfDoors;
    private float trunkCapacity;
    
    public Car(Long id, String make, String model, String description, int year, Color color) {
        super(id, make, model, description, year, color);
    }

    @Override
    public double getRentingCost() {
        return 0;
    }
}