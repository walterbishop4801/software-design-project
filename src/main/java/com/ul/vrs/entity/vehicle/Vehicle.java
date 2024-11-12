package com.ul.vrs.entity.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Vehicle: entity of a vehicle
 *
 * @author Rohan Sikder
 * @version 2.3.2
 * @since 1.0.0
 */


@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "type"
	)

@JsonSubTypes({
	    @JsonSubTypes.Type(value = Car.class, name = "car"),
	    @JsonSubTypes.Type(value = Truck.class, name = "truck") 
})


public abstract class Vehicle implements Subject {
    private long ID;
    private final String name;
    private final String brandOwner;
    private final int releaseYear;
    protected final double cost;
    private final Color color;
    private final Fuel fuelType;
    private VehicleState vehicleState;
    private List<Observer> observers;

    /**
     * Create new instance of Vehicle
     *
     * @param ID           id of the vehicle
     * @param name         name of the vehicle
     * @param brandOwner   brand owner of the vehicle
     * @param releaseYear  release year of the vehicle
     * @param cost         initial cost of the vehicle
     * @param color        color of the vehicle
     * @param fuelType     fuel type of the vehicle
     * @param vehicleState state of the vehicle
     */
    public Vehicle(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType, VehicleState vehicleState) {
        this.ID = ID;
        this.name = name;
        this.brandOwner = brandOwner;
        this.releaseYear = releaseYear;
        this.cost = cost;
        this.color = color;
        this.fuelType = fuelType;
        this.vehicleState = vehicleState;
        this.observers = new ArrayList<>();
    }

    /**
     * Create new instance of Vehicle
     *
     * @param ID           id of the vehicle
     * @param name         name of the vehicle
     * @param brandOwner   brand owner of the vehicle
     * @param releaseYear  release year of the vehicle
     * @param cost         initial cost of the vehicle
     * @param color        color of the vehicle
     * @param fuelType     fuel type of the vehicle
     */
    public Vehicle(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE);
    }

    /**
     * Get ID
     *
     * @return ID
     */
    public long getID() {
        return this.ID;
    }

    /**
     * Set ID to a new identifier
     *
     * @param ID new ID
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get brand owner
     *
     * @return brand owner
     */
    public String getBrandOwner() {
        return brandOwner;
    }

    /**
     * Get release year
     *
     * @return release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Get color
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get fuel type
     *
     * @return fuel type
     */
    public Fuel getFuelType() {
        return fuelType;
    }

    /**
     * Get renting cost of the current vehicle
     *
     * @return renting cost
     */
    public abstract double getRentingCost();

    /**
     * Get state of the vehicle
     *
     * @return state
     */
    public VehicleState getState() {
        return this.vehicleState;
    }

    /**
     * Update the state of the vehicle
     */
    public void updateState(VehicleState s) {
        this.vehicleState = s;
    }

    /**
     * Check if the vehicle is damaged
     *
     * @return check result
     */
    public boolean checkDamage() {
        return this.vehicleState.equals(VehicleState.DAMAGED);
    }

    /**
     * Check if the vehicle is available for renting
     *
     * @return check result
     */
    public boolean checkAvailability() {
        return this.vehicleState.equals(VehicleState.AVAILABLE);
    }

    @Override
    public void attach(Observer o) {
        if (!this.observers.contains(o)) {
            this.observers.add(o);
        }
    }

    @Override
    public void detach(Observer o) {
        if (this.observers.contains(o)) {
            this.observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.updateObserver();
        }
    }
}