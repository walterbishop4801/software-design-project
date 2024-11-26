package com.ul.vrs.entity.vehicle.fuel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
    double getCost();
}
