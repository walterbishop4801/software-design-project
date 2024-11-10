package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Van;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * VanFactory: factory to create instances of Van
 */
public class VanFactory extends VehicleFactory {
    
    private long ID;
    private String name;
    private String brandOwner;
    private int releaseYear;
    private double cost;
    private Color color;
    private Fuel fuelType;
    private float cargoCapacity;
    private int numberOfSeats;

    // Constructor for VanFactory to set properties
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
