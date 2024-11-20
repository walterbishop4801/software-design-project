package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class Scooter extends Vehicle {
    private final boolean hasHelmetIncluded;
    private final int maxPassengers;
    private final int rangePerFuelTank;

    @JsonCreator
    public Scooter(
            @JsonProperty("ID") long ID,
            @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner,
            @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost,
            @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType,
            @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("hasHelmetIncluded") boolean hasHelmetIncluded,
            @JsonProperty("maxPassengers") int maxPassengers,
            @JsonProperty("rangePerFuelTank") int rangePerFuelTank) {
        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.hasHelmetIncluded = hasHelmetIncluded;
        this.maxPassengers = maxPassengers;
        this.rangePerFuelTank = rangePerFuelTank;
    }

    // TODO: Do we need @JsonProperty here?
    public Scooter(
            long ID,
            String name,
            String brandOwner,
            int releaseYear,
            double cost,
            Color color,
            Fuel fuelType,
            boolean hasHelmetIncluded,
            int maxPassengers,
            int rangePerFuelTank) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, hasHelmetIncluded, maxPassengers, rangePerFuelTank);
    }

    public boolean isHasHelmetIncluded() {
        return hasHelmetIncluded;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public int getRangePerFuelTank() {
        return rangePerFuelTank;
    }

    // TODO: Adjust this value based on real-life costs or with some function
    @Override
    public double getRentingCost() {
        return 0;
    }
}
