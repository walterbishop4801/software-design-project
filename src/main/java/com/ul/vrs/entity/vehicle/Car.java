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

    // TODO: Do we need @JsonProperty here?
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

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public float getTrunkCapacity() {
        return trunkCapacity;
    }

    // TODO: Adjust this value based on real-life costs or with some function
    @Override
    public double getRentingCost() {
        return 0;
    }
}
