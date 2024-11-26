package com.ul.vrs.entity.vehicle.factory;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Scooter;
import com.ul.vrs.entity.vehicle.Truck;
import com.ul.vrs.entity.vehicle.Van;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class VehicleFactoryMethod {
    // Encapsulate constructor ...
    private VehicleFactoryMethod () { }

    public static final Vehicle createVehicle(String type, Object... params) {
        return switch (type) {
            case "Car" -> new CarFactory().createVehicle(
                    (long) params[0], (String) params[1], (String) params[2],
                    (int) params[3], (double) params[4], (Color) params[5],
                    (Fuel) params[6], (VehicleState)params[7], (int) params[8], (float) params[9]);

            case "Scooter" -> new ScooterFactory().createVehicle(
                    (long) params[0], (String) params[1], (String) params[2],
                    (int) params[3], (double) params[4], (Color) params[5],
                    (Fuel) params[6], (VehicleState) params[7], (boolean) params[8], (int) params[9], (int) params[10]);

            case "Truck" -> new TruckFactory().createVehicle(
                    (long) params[0], (String) params[1], (String) params[2],
                    (int) params[3], (double) params[4], (Color) params[5],
                    (Fuel) params[6], (VehicleState) params[7], (float) params[8], (float) params[9], (int) params[10]);

            case "Van" -> new VanFactory().createVehicle(
                    (long) params[0], (String) params[1], (String) params[2],
                    (int) params[3], (double) params[4], (Color) params[5],
                    (Fuel) params[6], (VehicleState) params[7], (float) params[8], (int) params[9]);

            default -> {
                System.err.println("VehicleFactoryMethod: unknown type");
                yield null;
            }
        };
    }

    public static final Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;

            return new CarFactory().createVehicle(
                car.getID(), car.getName(), car.getBrandOwner(),
                car.getReleaseYear(), car.getBaseCost(), car.getColor(),
                car.getFuelType(), car.getState(), car.getNumberOfDoors(),
                car.getTrunkCapacity()
            );
        }

        else if (vehicle instanceof Scooter) {
            Scooter scooter = (Scooter) vehicle;

            return new ScooterFactory().createVehicle(
                scooter.getID(), scooter.getName(), scooter.getBrandOwner(),
                scooter.getReleaseYear(), scooter.getBaseCost(), scooter.getColor(),
                scooter.getFuelType(), scooter.getState(), scooter.isHasHelmetIncluded(),
                scooter.getMaxPassengers(), scooter.getRangePerFuelTank()
            );
        }

        else if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;

            return new TruckFactory().createVehicle(
                truck.getID(), truck.getName(), truck.getBrandOwner(),
                truck.getReleaseYear(), truck.getBaseCost(), truck.getColor(),
                truck.getFuelType(), truck.getState(), truck.getPayloadCapacity(),
                truck.getTowingCapacity(), truck.getNumberOfAxles()
            );
        }

        else if (vehicle instanceof Van) {
            Van van = (Van) vehicle;

            return new VanFactory().createVehicle(
                van.getID(), van.getName(), van.getBrandOwner(),
                van.getReleaseYear(), van.getBaseCost(), van.getColor(),
                van.getFuelType(), van.getState(), van.getCargoCapacity(),
                van.getNumberOfSeats()
            );
        }

        return null;
    }
}