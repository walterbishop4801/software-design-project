package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.VehicleState;
import com.ul.vrs.jacoco.ExcludeConstructorFromGeneratedJacoco;
import com.ul.vrs.jacoco.ExcludeMethodFromGeneratedJacoco;

public class Scooter extends Vehicle {
    private final boolean hasHelmetIncluded;
    private final int maxPassengers;
    private final int rangePerFuelTank;

    @JsonCreator
    public Scooter(@JsonProperty("ID") long ID, @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner, @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost, @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType, @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("hasHelmetIncluded") boolean hasHelmetIncluded,
            @JsonProperty("maxPassengers") int maxPassengers,
            @JsonProperty("rangePerFuelTank") int rangePerFuelTank) {

        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.hasHelmetIncluded = hasHelmetIncluded;
        this.maxPassengers = maxPassengers;
        this.rangePerFuelTank = rangePerFuelTank;
    }

    // TODO: Do we need @JsonProperty here?
    @ExcludeConstructorFromGeneratedJacoco
    public Scooter(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, boolean hasHelmetIncluded, int maxPassengers, int rangePerFuelTank) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, new AvailableVehicleState(), hasHelmetIncluded, maxPassengers, rangePerFuelTank);
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

    @Override
    public double getRentingCost(int numberOfRentingDays) {
        double baseCost = this.getBaseCost() * numberOfRentingDays;
        double fuelCost = this.getFuelType().getCost() * numberOfRentingDays;
        double featureCost = 0.0;

        // Add extra cost when there is a helmet
        if (this.hasHelmetIncluded) {
            featureCost += 0.10 * numberOfRentingDays;
        }

        // Add extra cost based on passenger capacity
        if (this.maxPassengers > 2) {
            featureCost += this.maxPassengers * 10 * numberOfRentingDays;
        }

        // Add extra cost based on range per fuel tank
        if (this.rangePerFuelTank > 50) {
            featureCost += this.rangePerFuelTank * 15 * numberOfRentingDays;
        }

        return baseCost + fuelCost + featureCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + (hasHelmetIncluded ? 1231 : 1237);
        result = prime * result + maxPassengers;
        result = prime * result + rangePerFuelTank;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return hashCode() == ((Scooter) obj).hashCode();
        }

        return false;
    }

    @ExcludeMethodFromGeneratedJacoco
    @Override
    public String toString() {
        return "Scooter [hasHelmetIncluded=" + hasHelmetIncluded + ", maxPassengers=" + maxPassengers
                + ", rangePerFuelTank=" + rangePerFuelTank + ", cost=" + cost + ", isHasHelmetIncluded()="
                + isHasHelmetIncluded() + ", getMaxPassengers()=" + getMaxPassengers() + ", getID()=" + getID()
                + ", getRangePerFuelTank()=" + getRangePerFuelTank() + ", getName()=" + getName() + ", getBrandOwner()="
                + getBrandOwner() + ", getReleaseYear()=" + getReleaseYear() + ", getBaseCost()=" + getBaseCost()
                + ", getColor()=" + getColor() + ", getFuelType()=" + getFuelType() + ", getState()=" + getState()
                + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
                + "]";
    }
}