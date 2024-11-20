package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class Van extends Vehicle {
    private final float cargoCapacity;
    private final int numberOfSeats;

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

    // TODO: Do we need @JsonProperty here?
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

    public float getCargoCapacity() {
        return cargoCapacity;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    // TODO: Adjust this value based on real-life costs or with some function
    @Override
    public double getRentingCost() {
        return 0;
    }
}
