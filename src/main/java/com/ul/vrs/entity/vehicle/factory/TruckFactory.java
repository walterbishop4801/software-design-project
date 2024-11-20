package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Truck;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class TruckFactory implements VehicleFactory {
    private final long ID;
    private final String name;
    private final String brandOwner;
    private final int releaseYear;
    private final double cost;
    private final Color color;
    private final Fuel fuelType;
    private final float payloadCapacity;
    private final float towingCapacity;
    private final int numberOfAxles;

    public TruckFactory(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, float payloadCapacity, float towingCapacity, int numberOfAxles) {
        this.ID = ID;
        this.name = name;
        this.brandOwner = brandOwner;
        this.releaseYear = releaseYear;
        this.cost = cost;
        this.color = color;
        this.fuelType = fuelType;
        this.payloadCapacity = payloadCapacity;
        this.towingCapacity = towingCapacity;
        this.numberOfAxles = numberOfAxles;
    }

    @Override
    public Vehicle createVehicle() {
        return new Truck(ID, name, brandOwner, releaseYear, cost, color, fuelType, payloadCapacity, towingCapacity, numberOfAxles);
    }
}