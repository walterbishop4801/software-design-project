package com.ul.vrs.entity.vehicle;

import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * Truck: entity of a truck
 *
 * @author Rohan Sikder
 * @version 2.0.1
 * @since 1.0.0
 */
public class Truck extends Vehicle {
    private final float payloadCapacity;
    private final float towingCapacity;
    private final int numberOfAxles;

    /**
     * Create new instance of Truck
     *
     * @param brandOwner        brand owner of the vehicle
     * @param releaseYear       release year of the vehicle
     * @param cost              initial cost of the vehicle
     * @param color             color of the vehicle
     * @param fuelType          fuel type of the vehicle
     * @param vehicleState      state of the vehicle
     * @param payloadCapacity   payload capacity
     * @param towingCapacity    towing capacity
     * @param numberOfAxles     number of axles
     */
    public Truck(String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, VehicleState vehicleState, float payloadCapacity, float towingCapacity, int numberOfAxles) {
        super(brandOwner, releaseYear, cost, color, fuelType, vehicleState);

        this.payloadCapacity = payloadCapacity;
        this.towingCapacity = towingCapacity;
        this.numberOfAxles = numberOfAxles;
    }

    /**
     * Create new instance of Truck
     *
     * @param brandOwner        brand owner of the vehicle
     * @param releaseYear       release year of the vehicle
     * @param cost              initial cost of the vehicle
     * @param color             color of the vehicle
     * @param fuelType          fuel type of the vehicle
     * @param payloadCapacity   payload capacity
     * @param towingCapacity    towing capacity
     * @param numberOfAxles     number of axles
     */
    public Truck(String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, float payloadCapacity, float towingCapacity, int numberOfAxles) {
        this(brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, payloadCapacity, towingCapacity, numberOfAxles);
    }

    /**
     * Get payload capacity
     *
     * @return payload capacity
     */
    public float getPayloadCapacity() {
        return payloadCapacity;
    }

    /**
     * Get towing capacity
     *
     * @return towing capacity
     */
    public float getTowingCapacity() {
        return towingCapacity;
    }

    /**
     * Get number of axles
     *
     * @return number of axles
     */
    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    @Override
    public double getRentingCost() {
        return 0;
    }
}