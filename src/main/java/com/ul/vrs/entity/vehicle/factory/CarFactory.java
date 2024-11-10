package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class CarFactory extends VehicleFactory {
    private long ID;
    private String name;
    private String brandOwner;
    private int releaseYear;
    private double cost;
    private Color color;
    private Fuel fuelType;
    private int numberOfDoors;
    private float trunkCapacity;

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