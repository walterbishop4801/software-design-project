package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Scooter;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * ScooterFactory: factory to create instances of Scooter
 */
public class ScooterFactory extends VehicleFactory {
    
    private long ID;
    private String name;
    private String brandOwner;
    private int releaseYear;
    private double cost;
    private Color color;
    private Fuel fuelType;
    private boolean hasHelmetIncluded;
    private int maxPassengers;
    private int rangePerFuelTank;

    // Constructor for ScooterFactory to set properties
    public ScooterFactory(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, boolean hasHelmetIncluded, int maxPassengers, int rangePerFuelTank) {
        this.ID = ID;
        this.name = name;
        this.brandOwner = brandOwner;
        this.releaseYear = releaseYear;
        this.cost = cost;
        this.color = color;
        this.fuelType = fuelType;
        this.hasHelmetIncluded = hasHelmetIncluded;
        this.maxPassengers = maxPassengers;
        this.rangePerFuelTank = rangePerFuelTank;
    }

    @Override
    public Vehicle createVehicle() {
        return new Scooter(ID, name, brandOwner, releaseYear, cost, color, fuelType, hasHelmetIncluded, maxPassengers, rangePerFuelTank);
    }
}
