package com.ul.vrs.service;

import org.springframework.stereotype.Component;

@Component
public class VehicleManagerServiceHelper {

    private static VehicleManagerService vehicleManagerService;

    public VehicleManagerServiceHelper(VehicleManagerService service) {
        vehicleManagerService = service;
    }

    public static VehicleManagerService getVehicleManagerService() {
        return vehicleManagerService;
    }
}
