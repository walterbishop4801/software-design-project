package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.VehicleState;
import com.ul.vrs.jacoco.ExcludeConstructorFromGeneratedJacoco;
import com.ul.vrs.jacoco.ExcludeMethodFromGeneratedJacoco;

public class Van extends Vehicle {
    private final float cargoCapacity;
    private final int numberOfSeats;

    @JsonCreator
    public Van(@JsonProperty("ID") long ID, @JsonProperty("name") String name,
            @JsonProperty("brandOwner") String brandOwner, @JsonProperty("releaseYear") int releaseYear,
            @JsonProperty("cost") double cost, @JsonProperty("color") Color color,
            @JsonProperty("fuelType") Fuel fuelType, @JsonProperty("vehicleState") VehicleState vehicleState,
            @JsonProperty("cargoCapacity") float cargoCapacity, @JsonProperty("numberOfSeats") int numberOfSeats) {

        super(ID, name, brandOwner, releaseYear, cost, color, fuelType, vehicleState);
        this.cargoCapacity = cargoCapacity;
        this.numberOfSeats = numberOfSeats;
    }

    // TODO: Do we need @JsonProperty here?
    @ExcludeConstructorFromGeneratedJacoco
    public Van(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, float cargoCapacity, int numberOfSeats) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, new AvailableVehicleState(), cargoCapacity, numberOfSeats);
    }

    public float getCargoCapacity() {
        return cargoCapacity;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public double getRentingCost(int numberOfRentingDays) {
        double baseCost = this.getBaseCost() * numberOfRentingDays;
        double fuelCost = this.getFuelType().getCost() * numberOfRentingDays;
        double featureCost = 0.0;

        // Add extra cost for cargo capacity (+€15 per 100kg of cargo capacity per day)
        featureCost += this.cargoCapacity / 100 * 15 * numberOfRentingDays;

        // Add extra cost for the number of seats (+€10 per seat per day)
        featureCost += this.numberOfSeats * 10 * numberOfRentingDays;

        return baseCost + fuelCost + featureCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();

        result = prime * result + Float.floatToIntBits(cargoCapacity);
        result = prime * result + numberOfSeats;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return hashCode() == ((Van) obj).hashCode();
        }

        return false;
    }

    @ExcludeMethodFromGeneratedJacoco
    @Override
    public String toString() {
        return "Van [cargoCapacity=" + cargoCapacity + ", numberOfSeats=" + numberOfSeats + ", cost=" + cost
                + ", getCargoCapacity()=" + getCargoCapacity() + ", getNumberOfSeats()=" + getNumberOfSeats()
                + ", getID()=" + getID() + ", getName()=" + getName() + ", getBrandOwner()=" + getBrandOwner()
                + ", getReleaseYear()=" + getReleaseYear() + ", getBaseCost()=" + getBaseCost() + ", getColor()="
                + getColor() + ", getFuelType()=" + getFuelType() + ", hashCode()=" + hashCode() + ", getState()="
                + getState() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
    }
}