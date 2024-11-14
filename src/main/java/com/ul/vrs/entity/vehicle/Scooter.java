package com.ul.vrs.entity.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

/**
 * Scooter: entity of a scooter
 *
 * @version 2.3.2
 * @since 1.0.0
 */
public class Scooter extends Vehicle {
    private final boolean hasHelmetIncluded;
    private final int maxPassengers;
    private final int rangePerFuelTank;

    /**
     * Create new instance of Scooter
     *
     * @param ID                 id of the vehicle
     * @param name               name of the vehicle
     * @param brandOwner         brand owner of the vehicle
     * @param releaseYear        release year of the vehicle
     * @param cost               initial cost of the vehicle
     * @param color              color of the vehicle
     * @param fuelType           fuel type of the vehicle
     * @param vehicleState       state of the vehicle
     * @param hasHelmetIncluded  if helmet is included
     * @param maxPassengers      max number of passengers
     * @param rangePerFuelTank   range per fuel tank
     */
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

    /**
     * Create new instance of Scooter
     *
     * @param ID                 id of the vehicle
     * @param name               name of the vehicle
     * @param brandOwner         brand owner of the vehicle
     * @param releaseYear        release year of the vehicle
     * @param cost               initial cost of the vehicle
     * @param color              color of the vehicle
     * @param fuelType           fuel type of the vehicle
     * @param hasHelmetIncluded  if helmet is included
     * @param maxPassengers      max number of passengers
     * @param rangePerFuelTank   range per fuel tank
     */
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

    /**
     * Get if helmet is included
     *
     * @return if helmet is included
     */
    public boolean isHasHelmetIncluded() {
        return hasHelmetIncluded;
    }

    /**
     * Get max number of passengers
     *
     * @return max number of passengers
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * Get range per fuel tank
     *
     * @return range per fuel tank
     */
    public int getRangePerFuelTank() {
        return rangePerFuelTank;
    }

    @Override
    public double getRentingCost() {
        // renting cost logic
        return 0;
    }
}
