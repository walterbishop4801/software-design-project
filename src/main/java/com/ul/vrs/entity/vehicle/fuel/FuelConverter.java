package com.ul.vrs.entity.vehicle.fuel;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FuelConverter implements AttributeConverter<Fuel, String> {
    @Override
    public String convertToDatabaseColumn(Fuel fuel) {
        if (fuel instanceof PetrolFuel) {
            return "petrol";
        } else if (fuel instanceof DieselFuel) {
            return "diesel";
        } else if (fuel instanceof ElectricityFuel) {
            return "electric";
        }
        return null;
    }

    @Override
    public Fuel convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "petrol" -> new PetrolFuel();
            case "diesel" -> new DieselFuel();
            case "electric" -> new ElectricityFuel();
            default -> throw new IllegalArgumentException("Unknown fuel type: " + dbData);
        };
    }
}
