package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Scooter;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class ScooterFactory implements VehicleFactory {
    private final long ID;
    private final String name;
    private final String brandOwner;
    private final int releaseYear;
    private final double cost;
    private final Color color;
    private final Fuel fuelType;
    private final boolean hasHelmetIncluded;
    private final int maxPassengers;
    private final int rangePerFuelTank;

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