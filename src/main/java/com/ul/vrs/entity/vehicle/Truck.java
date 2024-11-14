package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * Truck: entity of a truck
 *
 * @version 2.3.2
 * @since 1.0.0
 */
public class Truck extends Vehicle {
    private final float payloadCapacity;
    private final float towingCapacity;
    private final int numberOfAxles;

    /**
     * Create new instance of Truck
     *
     * @param ID                id of the vehicle
     * @param name              name of the vehicle
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
    @JsonCreator
    public Truck(
            @JsonProperty("ID") long ID,
            @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner,
            @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost,
            @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType,
            @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("payloadCapacity") float payloadCapacity,
            @JsonProperty("towingCapacity") float towingCapacity,
            @JsonProperty("numberOfAxles") int numberOfAxles) {
        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.payloadCapacity = payloadCapacity;
        this.towingCapacity = towingCapacity;
        this.numberOfAxles = numberOfAxles;
    }

    /**
     * Create new instance of Truck
     *
     * @param ID                id of the vehicle
     * @param name              name of the vehicle
     * @param brandOwner        brand owner of the vehicle
     * @param releaseYear       release year of the vehicle
     * @param cost              initial cost of the vehicle
     * @param color             color of the vehicle
     * @param fuelType          fuel type of the vehicle
     * @param payloadCapacity   payload capacity
     * @param towingCapacity    towing capacity
     * @param numberOfAxles     number of axles
     */
    public Truck(
            long ID,
            String name,
            String brandOwner,
            int releaseYear,
            double cost,
            Color color,
            Fuel fuelType,
            float payloadCapacity,
            float towingCapacity,
            int numberOfAxles) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, payloadCapacity, towingCapacity, numberOfAxles);
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
