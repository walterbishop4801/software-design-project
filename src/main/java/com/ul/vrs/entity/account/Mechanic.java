package com.ul.vrs.entity.account;

import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;

import java.util.ArrayList;
import java.util.List;

public class Mechanic implements Observer {
    private String name;
    private List<Vehicle> assignedVehicles;

    // Constructor
    public Mechanic(String name) {
        this.name = name;
        this.assignedVehicles = new ArrayList<>();
    }

    // Getter for the mechanic's name
    public String getName() {
        return name;
    }

    // Assign a vehicle to the mechanic
    public void assignVehicle(Vehicle v) {
        if (v != null && v.checkAvailability()) {
            v.updateState(VehicleState.IN_MAINTENANCE); // Mark vehicle as in maintenance
            assignedVehicles.add(v);
            v.attach(this); // Attach the mechanic as an observer
            System.out.println("Vehicle ID: " + v.getID() + " assigned to Mechanic: " + name);
        } else {
            System.out.println("Vehicle is either null or not available for maintenance.");
        }
    }

    // Liberate (release) a vehicle from the mechanic
    public void liberateVehicle(Vehicle v) {
        if (v != null && assignedVehicles.contains(v)) {
            v.updateState(VehicleState.AVAILABLE); // Mark vehicle as available
            assignedVehicles.remove(v);
            v.detach(this); // Detach the mechanic as an observer
            System.out.println("Vehicle ID: " + v.getID() + " liberated by Mechanic: " + name);
        } else {
            System.out.println("Vehicle is either null or not assigned to this mechanic.");
        }
    }

    // Service a vehicle (simulate servicing)
    public void serviceVehicle(Vehicle v) {
        if (v != null && assignedVehicles.contains(v)) {
            System.out.println("Mechanic " + name + " is servicing Vehicle ID: " + v.getID());
            // Add custom logic for servicing if needed
        } else {
            System.out.println("Vehicle is not assigned to this mechanic.");
        }
    }

    // Fix a damaged vehicle
    public void fixVehicle(Vehicle v) {
        if (v != null && v.getState() == VehicleState.DAMAGED) {
            System.out.println("Mechanic " + name + " is fixing Vehicle ID: " + v.getID());
            v.updateState(VehicleState.AVAILABLE); // Mark vehicle as available after fixing
        } else {
            System.out.println("Vehicle is either not damaged or not valid.");
        }
    }

    // Observer method implementation (called when a vehicle's state changes)
    @Override
    public void updateObserver() {
        System.out.println("Mechanic " + name + " has been notified of a vehicle state change.");
        // Add additional logic to handle notification if required
        
    }
}
