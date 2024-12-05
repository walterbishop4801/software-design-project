package com.ul.vrs.entity.vehicle.state;

import com.ul.vrs.entity.vehicle.Vehicle;

public interface VehicleState {
    boolean check(Class<? extends VehicleState> className);
    void handleRequest(Vehicle vehicle);
}
