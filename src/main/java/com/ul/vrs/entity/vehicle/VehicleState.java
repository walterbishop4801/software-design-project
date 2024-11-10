package com.ul.vrs.entity.vehicle;

/**
 * VehicleState: state of a vehicle
 *
 * @author Rohan Sikder
 * @version 1.0.0
 * @since 1.0.0
 */
public enum VehicleState {
    AVAILABLE,              // vehicle is available for renting
    RESERVED,               // vehicle has already been reserved
    DAMAGED,                // vehicle is damaged, a mechanic has to fix it
    IN_MAINTENANCE;         // vehicle is currently in maintenance
}