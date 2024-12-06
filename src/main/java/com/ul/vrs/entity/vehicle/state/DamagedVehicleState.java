package com.ul.vrs.entity.vehicle.state;

import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.VehicleManagerService;

public class DamagedVehicleState implements VehicleState {
    @Override
    public boolean check(Class<? extends VehicleState> className) {
        return this.getClass().getName().equals(className.getName());
    }

    @Override
    public void handleRequest(Vehicle vehicle) {
        if (vehicle != null) {
            vehicle.notifyObservers();
            VehicleManagerService.getInstance().updateVehicle(vehicle.getID(), vehicle);
        }
    }

    @Override
    public int hashCode() {
        return 1001;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }
}