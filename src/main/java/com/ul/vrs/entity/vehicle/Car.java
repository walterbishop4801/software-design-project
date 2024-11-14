package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * Car: entity of a car
 *
 * @version 2.3.2
 * @since 1.0.0
 */
public class Car extends Vehicle {
    private final int numberOfDoors;
    private final float trunkCapacity;

    /**
     * Create new instance of Car
     *
     * @param ID            id of the vehicle
     * @param name          name of the vehicle
     * @param brandOwner    brand owner of the vehicle
     * @param releaseYear   release year of the vehicle
     * @param cost          initial cost of the vehicle
     * @param color         color of the vehicle
     * @param fuelType      fuel type of the vehicle
     * @param vehicleState  state of the vehicle
     * @param numberOfDoors number of doors
     * @param trunkCapacity trunk capacity
     */
    @JsonCreator
    public Car(
            @JsonProperty("ID") long ID,
            @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner,
            @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost,
            @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType,
            @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("numberOfDoors") int numberOfDoors,
            @JsonProperty("trunkCapacity") float trunkCapacity) {
        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.numberOfDoors = numberOfDoors;
        this.trunkCapacity = trunkCapacity;
    }

    /**
     * Create new instance of Car
     *
     * @param ID            id of the vehicle
     * @param name          name of the vehicle
     * @param brandOwner    brand owner of the vehicle
     * @param releaseYear   release year of the vehicle
     * @param cost          initial cost of the vehicle
     * @param color         color of the vehicle
     * @param fuelType      fuel type of the vehicle
     * @param numberOfDoors number of doors
     * @param trunkCapacity trunk capacity
     */
    public Car(
            long ID,
            String name,
            String brandOwner,
            int releaseYear,
            double cost,
            Color color,
            Fuel fuelType,
            int numberOfDoors,
            float trunkCapacity) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, numberOfDoors, trunkCapacity);
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
