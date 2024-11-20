package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class Truck extends Vehicle {
    private final float payloadCapacity;
    private final float towingCapacity;
    private final int numberOfAxles;

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

    // TODO: Do we need @JsonProperty here?
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

    public float getPayloadCapacity() {
        return payloadCapacity;
    }

    public float getTowingCapacity() {
        return towingCapacity;
    }

    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    // TODO: Adjust this value based on real-life costs or with some function
    @Override
    public double getRentingCost() {
        return 0;
    }
}
