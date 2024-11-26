package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.jacoco.ExcludeConstructorFromGeneratedJacoco;
import com.ul.vrs.jacoco.ExcludeMethodFromGeneratedJacoco;

public class Car extends Vehicle {
    private final int numberOfDoors;
    private final float trunkCapacity;

    @JsonCreator
    public Car(@JsonProperty("ID") long ID, @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner, @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost, @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType, @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("numberOfDoors") int numberOfDoors, @JsonProperty("trunkCapacity") float trunkCapacity) {

        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.numberOfDoors = numberOfDoors;
        this.trunkCapacity = trunkCapacity;
    }

    // TODO: Do we need @JsonProperty here?
    @ExcludeConstructorFromGeneratedJacoco
    public Car(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, int numberOfDoors, float trunkCapacity) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE, numberOfDoors, trunkCapacity);
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public float getTrunkCapacity() {
        return trunkCapacity;
    }

    @Override
    public double getRentingCost(int numberOfRentingDays) {
        double fuelCost = this.getFuelType().getCost() * numberOfRentingDays;
        double baseCost = this.getBaseCost() * numberOfRentingDays;
        double featureCost = 0.0;

        // Add extra cost for more doors
        if (this.numberOfDoors > 4) {
            featureCost += 50 * numberOfRentingDays;
        }

        // Add extra cost for larger trunk capacity
        if (this.trunkCapacity > 300) {
            featureCost += 30 * numberOfRentingDays;
        }

        return fuelCost + baseCost + featureCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + numberOfDoors;
        result = prime * result + Float.floatToIntBits(trunkCapacity);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return hashCode() == ((Car) obj).hashCode();
        }

        return false;
    }

    @ExcludeMethodFromGeneratedJacoco
    @Override
    public String toString() {
        return "Car [numberOfDoors=" + numberOfDoors + ", trunkCapacity=" + trunkCapacity + ", cost=" + cost
                + ", getNumberOfDoors()=" + getNumberOfDoors() + ", getTrunkCapacity()=" + getTrunkCapacity()
                + ", getID()=" + getID() + ", getName()=" + getName() + ", getBrandOwner()=" + getBrandOwner()
                + ", getReleaseYear()=" + getReleaseYear() + ", getBaseCost()=" + getBaseCost() + ", hashCode()="
                + hashCode() + ", getColor()=" + getColor() + ", getFuelType()=" + getFuelType() + ", getState()="
                + getState() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
    }
}