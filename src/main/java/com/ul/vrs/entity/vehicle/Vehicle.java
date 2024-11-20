package com.ul.vrs.entity.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    public Vehicle(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE);
    }

    public long getID() {
        return this.ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Color getColor() {
        return color;
    }

    public Fuel getFuelType() {
        return fuelType;
    }

    public abstract double getRentingCost();

    public VehicleState getState() {
        return this.vehicleState;
    }

    public void updateState(VehicleState s) {
        this.vehicleState = s;
    }

    public boolean checkDamage() {
        return this.vehicleState.equals(VehicleState.DAMAGED);
    }

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