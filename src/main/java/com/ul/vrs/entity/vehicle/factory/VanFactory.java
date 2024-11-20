package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Van;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class VanFactory implements VehicleFactory {
    private final long ID;
    private final String name;
    private final String brandOwner;
    private final int releaseYear;
    private final double cost;
    private final Color color;
    private final Fuel fuelType;
    private final float cargoCapacity;
    private final int numberOfSeats;

    public VanFactory(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, float cargoCapacity, int numberOfSeats) {
        this.ID = ID;
        this.name = name;
        this.brandOwner = brandOwner;
        this.releaseYear = releaseYear;
        this.cost = cost;
        this.color = color;
        this.fuelType = fuelType;
        this.cargoCapacity = cargoCapacity;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public Vehicle createVehicle() {
        return new Van(ID, name, brandOwner, releaseYear, cost, color, fuelType, cargoCapacity, numberOfSeats);
    }
}