package com.ul.vrs.entity.vehicle;

public enum VehicleState {
    AVAILABLE,              // vehicle is available for renting
    RESERVED,               // vehicle has already been reserved
    DAMAGED,                // vehicle is damaged, a mechanic has to fix it
    IN_MAINTENANCE;         // vehicle is currently in maintenance
}