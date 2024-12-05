package com.ul.vrs.entity.account;

import com.ul.vrs.entity.Observer;
import com.ul.vrs.entity.Subject;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.DamagedVehicleState;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;

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
        if (v != null && v.getState().check(AvailableVehicleState.class)) {
            v.updateState(new InMaintenanceVehicleState()); // Mark vehicle as in maintenance
            v.attach(this); // Attach this mechanic as an observer
            System.out.println("Vehicle ID: " + v.getID() + " assigned to Mechanic: " + name);
        } else {
            System.out.println("Vehicle is either null or not available for maintenance.");
        }
    }

    // Detach the mechanic as an observer from a vehicle
    public void releaseFromVehicle(Vehicle v) {
        if (v != null) {
            System.out.println("Releasing mechanic from vehicle with ID: " + v.getID());
            v.updateState(new AvailableVehicleState()); // Update state to AVAILABLE
            v.detach(this); // Detach mechanic as observer
            System.out.println("Mechanic released from vehicle with ID: " + v.getID());
        } else {
            System.out.println("Cannot release mechanic: Vehicle is null.");
        }
    }

    // Service a vehicle
    public void serviceVehicle(Vehicle v) {
        if (v != null && v.getState().check(InMaintenanceVehicleState.class)) {
            System.out.println("Mechanic " + name + " is servicing Vehicle ID: " + v.getID());
        } else {
            System.out.println("Vehicle is not in maintenance.");
        }
    }

    // Fix a damaged vehicle
    public void fixVehicle(Vehicle v) {
        if (v != null && v.getState().check(DamagedVehicleState.class)) {
            System.out.println("Mechanic " + name + " is fixing Vehicle ID: " + v.getID());
            v.updateState(new AvailableVehicleState()); // Mark vehicle as available after fixing
        } else {
            System.out.println("Vehicle is either not damaged or invalid.");
        }
    }

    // Observer method implementation
    @Override
    public void updateObserver(Subject subject) {
        System.out.println("Mechanic " + name + " has been notified of a vehicle state change.");
    }
}
