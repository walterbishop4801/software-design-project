package com.ul.vrs.entity.account;

import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;

public class Mechanic implements Observer {
    private String name;

    // Constructor
    public Mechanic(String name) {
        this.name = name;
    }

    // Getter for the mechanic's name
    public String getName() {
        return name;
    }

    // Assign a mechanic as an observer to a vehicle
    public void assignToVehicle(Vehicle v) {
        if (v != null && v.checkAvailability()) {
            v.updateState(VehicleState.IN_MAINTENANCE); // Mark vehicle as in maintenance
            v.attach(this); // Attach this mechanic as an observer
            System.out.println("Vehicle ID: " + v.getID() + " assigned to Mechanic: " + name);
        } else {
            System.out.println("Vehicle is either null or not available for maintenance.");
        }
    }

    // Detach the mechanic as an observer from a vehicle
    public void releaseFromVehicle(Vehicle v) {
        if (v != null) {
            v.updateState(VehicleState.AVAILABLE); // Mark vehicle as available
            v.detach(this); // Detach this mechanic as an observer
            System.out.println("Mechanic: " + name + " released from Vehicle ID: " + v.getID());
        } else {
            System.out.println("Vehicle is invalid.");
        }
    }

    // Service a vehicle
    public void serviceVehicle(Vehicle v) {
        if (v != null && v.getState() == VehicleState.IN_MAINTENANCE) {
            System.out.println("Mechanic " + name + " is servicing Vehicle ID: " + v.getID());
            // Add custom servicing logic here if required
        } else {
            System.out.println("Vehicle is not in maintenance.");
        }
    }

    // Fix a damaged vehicle
    public void fixVehicle(Vehicle v) {
        if (v != null && v.getState() == VehicleState.DAMAGED) {
            System.out.println("Mechanic " + name + " is fixing Vehicle ID: " + v.getID());
            v.updateState(VehicleState.AVAILABLE); // Mark vehicle as available after fixing
        } else {
            System.out.println("Vehicle is either not damaged or invalid.");
        }
    }

    // Observer method implementation
    @Override
    public void updateObserver() {
        System.out.println("Mechanic " + name + " has been notified of a vehicle state change.");
        // Add additional logic to handle notification if required
    }
}