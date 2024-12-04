package com.ul.vrs.entity.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.fuel.Fuel;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;
import com.ul.vrs.jacoco.ExcludeConstructorFromGeneratedJacoco;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Car.class, name = "car"),
    @JsonSubTypes.Type(value = Scooter.class, name = "scooter"),
    @JsonSubTypes.Type(value = Truck.class, name = "truck"),
    @JsonSubTypes.Type(value = Van.class, name = "van")
})
public abstract class Vehicle implements Subject {
    @Id
    private long ID;
    private final String name;
    private final String brandOwner;
    private final int releaseYear;
    protected final double cost;
    @Enumerated(EnumType.STRING)
    private final Color color;
    private final Fuel fuelType;
    @Enumerated(EnumType.STRING)
    private VehicleState vehicleState;
    @Transient
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

    public Vehicle(){
        this.ID = 100;
        this.name = "Camry";
        this.brandOwner = "Toyota";
        this.releaseYear = 2020;
        this.cost = 25_000;
        this.color = Color.WHITE;
        this.fuelType = new PetrolFuel();
        this.vehicleState = VehicleState.AVAILABLE;
        this.observers = new ArrayList<>();
    }

    @ExcludeConstructorFromGeneratedJacoco
    public Vehicle(long ID, String name, String brandOwner, int releaseYear, double cost, Color color, Fuel fuelType) {
        this(ID, name, brandOwner, releaseYear, cost, color, fuelType, VehicleState.AVAILABLE);
    }

    public abstract double getRentingCost(int numberOfRentingDays);

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

    public double getBaseCost() {
        return cost;
    }

    public Color getColor() {
        return color;
    }

    public Fuel getFuelType() {
        return fuelType;
    }

    public VehicleState getState() {
        return this.vehicleState;
    }

    public void updateState(VehicleState state) {
        this.vehicleState = state;
        this.vehicleState.handleRequest(this);
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
            observer.updateObserver(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (ID ^ (ID >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((brandOwner == null) ? 0 : brandOwner.hashCode());
        result = prime * result + releaseYear;

        long temp;
        temp = Double.doubleToLongBits(cost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
        result = prime * result + ((vehicleState == null) ? 0 : vehicleState.hashCode());
        result = prime * result + ((observers == null) ? 0 : observers.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return hashCode() == ((Vehicle) obj).hashCode();
        }

        return false;
    }
}