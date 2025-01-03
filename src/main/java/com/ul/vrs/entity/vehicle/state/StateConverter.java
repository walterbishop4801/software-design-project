package com.ul.vrs.entity.vehicle.state;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<VehicleState, String> {
    @Override
    public String convertToDatabaseColumn(VehicleState state) {
        if (state instanceof AvailableVehicleState) {
            return "available";
        } else if (state instanceof DamagedVehicleState) {
            return "damaged";
        } else if (state instanceof InMaintenanceVehicleState) {
            return "in_maintenance";
        } else if (state instanceof ReservedVehicleState) {
            return "reserved";
        }

        return null;
    }

    @Override
    public VehicleState convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            throw new IllegalArgumentException("Database value for VehicleState cannot be null or blank.");
        }

        return switch (dbData.toLowerCase()) { // Handle case insensitivity
            case "available" -> new AvailableVehicleState();
            case "damaged" -> new DamagedVehicleState();
            case "in_maintenance" -> new InMaintenanceVehicleState();
            case "reserved" -> new ReservedVehicleState();
            default -> throw new IllegalArgumentException("Unknown state type: " + dbData);
        };
    }
}