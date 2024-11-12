package com.ul.vrs.entity.vehicle.fuel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Fuel: fuel abstraction
 *
 * @version 2.3.2
 * @since 1.0.0
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PetrolFuel.class, name = "petrol"),
    @JsonSubTypes.Type(value = DieselFuel.class, name = "diesel"),
    @JsonSubTypes.Type(value = ElectricityFuel.class, name = "electric")
})
public interface Fuel {
    /**
     * Get cost of the fuel
     *
     * @return cost of the fuel
     */
    double getCost();
}
