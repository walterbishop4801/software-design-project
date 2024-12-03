package com.ul.vrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleManagerServiceHelper {

    private static VehicleManagerService vehicleManagerService;

    @Autowired
    public VehicleManagerServiceHelper(VehicleManagerService service) {
        vehicleManagerService = service;
    }

    public static VehicleManagerService getVehicleManagerService() {
        return vehicleManagerService;
    }
}
