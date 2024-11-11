package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.Truck;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * TruckFactory: factory to create instances of Truck
 */
public class TruckFactory extends VehicleFactory {
    
    private long ID;
    private String name;
    private String brandOwner;
    private int releaseYear;
    private double cost;
    private Color color;
    private Fuel fuelType;
    private float payloadCapacity;
    private float towingCapacity;
    private int numberOfAxles;

    // Constructor for TruckFactory to set properties
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
