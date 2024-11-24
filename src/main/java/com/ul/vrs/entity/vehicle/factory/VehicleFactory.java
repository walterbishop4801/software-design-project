package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public abstract class VehicleFactory {
    public abstract Vehicle createVehicle();

    // Factory Method to dynamically create the appropriate factory
    public static VehicleFactory getFactory(String vehicleType, long id, String name, String brandOwner, int releaseYear, 
                                            double cost, Color color, Fuel fuelType, Object... additionalParams) {
        switch (vehicleType.toLowerCase()) {
            case "car":
                if (additionalParams.length >= 2) {
                    int numberOfDoors = (int) additionalParams[0];
                    float trunkCapacity = (float) additionalParams[1];
                    return new CarFactory(id, name, brandOwner, releaseYear, cost, color, fuelType, numberOfDoors, trunkCapacity);
                }
                break;
            case "scooter":
                if (additionalParams.length >= 3) {
                    boolean hasHelmetIncluded = (boolean) additionalParams[0];
                    int maxPassengers = (int) additionalParams[1];
                    int rangePerFuelTank = (int) additionalParams[2];
                    return new ScooterFactory(id, name, brandOwner, releaseYear, cost, color, fuelType, hasHelmetIncluded, maxPassengers, rangePerFuelTank);
                }
                break;
            case "truck":
                if (additionalParams.length >= 3) {
                    float payloadCapacity = (float) additionalParams[0];
                    float towingCapacity = (float) additionalParams[1];
                    int numberOfAxles = (int) additionalParams[2];
                    return new TruckFactory(id, name, brandOwner, releaseYear, cost, color, fuelType, payloadCapacity, towingCapacity, numberOfAxles);
                }
                break;
            case "van":
                if (additionalParams.length >= 2) {
                    float cargoCapacity = (float) additionalParams[0];
                    int numberOfSeats = (int) additionalParams[1];
                    return new VanFactory(id, name, brandOwner, releaseYear, cost, color, fuelType, cargoCapacity, numberOfSeats);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
        throw new IllegalArgumentException("Invalid parameters for vehicle type: " + vehicleType);
    }
}
