package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class CarFactory implements VehicleFactory {
    private final long ID;
    private final String name;
    private final String brandOwner;
    private final int releaseYear;
    private final double cost;
    private final Color color;
    private final Fuel fuelType;
    private final int numberOfDoors;
    private final float trunkCapacity;

    public CarFactory(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, int numberOfDoors, float trunkCapacity) {
        this.ID = ID;
        this.name = name;
        this.brandOwner = brandOwner;
        this.releaseYear = releaseYear;
        this.cost = cost;
        this.color = color;
        this.fuelType = fuelType;
        this.numberOfDoors = numberOfDoors;
        this.trunkCapacity = trunkCapacity;
    }

    @Override
    public Vehicle createVehicle() {
        return new Car(ID, name, brandOwner, releaseYear, cost, color, fuelType, numberOfDoors, trunkCapacity);
    }
}