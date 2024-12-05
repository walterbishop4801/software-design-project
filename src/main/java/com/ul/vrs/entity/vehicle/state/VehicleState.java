package com.ul.vrs.entity.vehicle.state;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ul.vrs.entity.vehicle.Vehicle;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AvailableVehicleState.class, name = "Available"),
    @JsonSubTypes.Type(value = DamagedVehicleState.class, name = "Damaged"),
    @JsonSubTypes.Type(value = InMaintenanceVehicleState.class, name = "InMaintenance"),
    @JsonSubTypes.Type(value = ReservedVehicleState.class, name = "Reserved")
})
public interface VehicleState {
    boolean check(Class<? extends VehicleState> className);
    void handleRequest(Vehicle vehicle);
}
