package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * Van: entity of a van
 *
 * @version 2.3.2
 * @since 1.0.0
 */
public class Van extends Vehicle {
    private final float cargoCapacity;
    private final int numberOfSeats;

    /**
     * Create new instance of Van
     *
     * @param ID            id of the vehicle
     * @param name          name of the vehicle
     * @param brandOwner    brand owner of the vehicle
     * @param releaseYear   release year of the vehicle
     * @param cost          initial cost of the vehicle
     * @param color         color of the vehicle
     * @param fuelType      fuel type of the vehicle
     * @param vehicleState  state of the vehicle
     * @param cargoCapacity cargo capacity
     * @param numberOfSeats number of seats
     */
    @JsonCreator
    public Van(
            @JsonProperty("ID") long ID,
            @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner,
            @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost,
            @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType,
            @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("cargoCapacity") float cargoCapacity,
            @JsonProperty("numberOfSeats") int numberOfSeats) {
        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.cargoCapacity = cargoCapacity;
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Create new instance of Van
     *
     * @param ID            id of the vehicle
     * @param name          name of the vehicle
     * @param brandOwner    brand owner of the vehicle
     * @param releaseYear   release year of the vehicle
     * @param cost          initial cost of the vehicle
     * @param color         color of the vehicle
     * @param fuelType      fuel type of the vehicle
     * @param cargoCapacity cargo capacity
     * @param numberOfSeats number of seats
     */
    public Van(
            long ID,
            String name,
            String brandOwner,
            int releaseYear,
            double cost,
            Color color,
            Fuel fuelType,
            float cargoCapacity,
            int numberOfSeats) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, cargoCapacity, numberOfSeats);
    }

    /**
     * Get cargo capacity
     *
     * @return cargo capacity
     */
    public float getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * Get number of seats
     *
     * @return number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public double getRentingCost() {
        return 0;
    }
}
