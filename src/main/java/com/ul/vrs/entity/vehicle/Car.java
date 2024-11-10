package com.ul.vrs.entity.vehicle;

import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * Car: entity of a car
 *
 * @author Rohan Sikder
 * @version 2.0.1
 * @since 1.0.0
 */
public class Car extends Vehicle {
    private final int numberOfDoors;
    private final float trunkCapacity;

    /**
     * Create new instance of Car
     *
     * @param brandOwner    brand owner of the vehicle
     * @param releaseYear   release year of the vehicle
     * @param cost          initial cost of the vehicle
     * @param color         color of the vehicle
     * @param fuelType      fuel type of the vehicle
     * @param vehicleState  state of the vehicle
     * @param numberOfDoors number of doors
     * @param trunkCapacity trunk capacity
     */
    public Car(String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, VehicleState vehicleState, int numberOfDoors, float trunkCapacity) {
        super(brandOwner, releaseYear, cost, color, fuelType, vehicleState);

        this.numberOfDoors = numberOfDoors;
        this.trunkCapacity = trunkCapacity;
    }

    /**
     * Create new instance of Car
     *
     * @param brandOwner    brand owner of the vehicle
     * @param releaseYear   release year of the vehicle
     * @param cost          initial cost of the vehicle
     * @param color         color of the vehicle
     * @param fuelType      fuel type of the vehicle
     * @param numberOfDoors number of doors
     * @param trunkCapacity trunk capacity
     */
    public Car(String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, int numberOfDoors, float trunkCapacity) {
        this(brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, numberOfDoors, trunkCapacity);
    }

    /**
     * Get number of doors
     *
     * @return number of doors
     */
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * Get trunk capacity
     *
     * @return trunk capacity
     */
    public float getTrunkCapacity() {
        return trunkCapacity;
    }

    @Override
    public double getRentingCost() {
        return 0;
    }
}