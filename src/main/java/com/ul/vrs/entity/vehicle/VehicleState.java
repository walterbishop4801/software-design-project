package com.ul.vrs.entity.vehicle;

import com.ul.vrs.service.VehicleManagerService;

// TODO: This is not really extensible, so we should split it into classes
public enum VehicleState {
    AVAILABLE {
        @Override
        public void handleRequest(Vehicle vehicle) {
            if (vehicle != null) {
                VehicleManagerService.getInstance().updateVehicle(vehicle.getID(), vehicle);
            }
        }
    },

    RESERVED {
        @Override
        public void handleRequest(Vehicle vehicle) {
            if (vehicle != null) {
                VehicleManagerService.getInstance().updateVehicle(vehicle.getID(), vehicle);
            }
        }
    },

    DAMAGED {
        @Override
        public void handleRequest(Vehicle vehicle) {
            if (vehicle != null) {
                vehicle.notifyObservers();
                VehicleManagerService.getInstance().updateVehicle(vehicle.getID(), vehicle);
            }
        }
    },

    IN_MAINTENANCE {
        @Override
        public void handleRequest(Vehicle vehicle) {
            if (vehicle != null) {
                vehicle.notifyObservers();
                VehicleManagerService.getInstance().updateVehicle(vehicle.getID(), vehicle);
            }
        }
    };

    public abstract void handleRequest(Vehicle vehicle);
}