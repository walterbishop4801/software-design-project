package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.jacoco.ExcludeConstructorFromGeneratedJacoco;
import com.ul.vrs.jacoco.ExcludeMethodFromGeneratedJacoco;

import jakarta.persistence.*;

@Entity
public class Truck extends Vehicle {
    private final float payloadCapacity;
    private final float towingCapacity;
    private final int numberOfAxles;

    @JsonCreator
    public Truck(@JsonProperty("ID") long ID, @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner, @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost, @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType, @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("payloadCapacity") float payloadCapacity,
            @JsonProperty("towingCapacity") float towingCapacity,
            @JsonProperty("numberOfAxles") int numberOfAxles) {

        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.payloadCapacity = payloadCapacity;
        this.towingCapacity = towingCapacity;
        this.numberOfAxles = numberOfAxles;
    }
    
    public Truck() {
        this.payloadCapacity = 100;
        this.towingCapacity = 2000;
        this.numberOfAxles = 2;
    }


    // TODO: Do we need @JsonProperty here?
    @ExcludeConstructorFromGeneratedJacoco
    public Truck(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, float payloadCapacity, float towingCapacity, int numberOfAxles) {
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

    @Override
    public double getRentingCost(int numberOfRentingDays) {
        double baseCost = this.getBaseCost() * numberOfRentingDays;
        double fuelCost = this.getFuelType().getCost() * numberOfRentingDays;
        double featureCost = 0.0;

        // Add extra cost for payload capacity (+€20 per ton of payload capacity per day)
        featureCost += this.payloadCapacity * 20 * numberOfRentingDays;

        // Add extra cost for towing capacity (+€30 for every ton of towing capacity per day)
        featureCost += this.towingCapacity * 30 * numberOfRentingDays;

        // Add extra cost based on the number of axles
        featureCost += this.numberOfAxles * 10 * numberOfRentingDays;

        return baseCost + fuelCost + featureCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + Float.floatToIntBits(payloadCapacity);
        result = prime * result + Float.floatToIntBits(towingCapacity);
        result = prime * result + numberOfAxles;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return hashCode() == ((Truck) obj).hashCode();
        }

        return false;
    }

    @ExcludeMethodFromGeneratedJacoco
    @Override
    public String toString() {
        return "Truck [payloadCapacity=" + payloadCapacity + ", towingCapacity=" + towingCapacity + ", numberOfAxles="
                + numberOfAxles + ", cost=" + cost + ", getPayloadCapacity()=" + getPayloadCapacity()
                + ", getTowingCapacity()=" + getTowingCapacity() + ", getNumberOfAxles()=" + getNumberOfAxles()
                + ", getID()=" + getID() + ", getName()=" + getName() + ", getBrandOwner()=" + getBrandOwner()
                + ", getReleaseYear()=" + getReleaseYear() + ", getBaseCost()=" + getBaseCost() + ", getColor()="
                + getColor() + ", getFuelType()=" + getFuelType() + ", getState()=" + getState() + ", hashCode()="
                + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
    }
}