package com.ul.vrs.controller;

import com.ul.vrs.entity.account.Account;
import com.ul.vrs.entity.account.Manager;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.AccountManagerService;
import com.ul.vrs.service.VehicleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleManagerService vehicleService;

    @Autowired
    private AccountManagerService accountManagerService;

    // -------------------------------------------
    // Check user permissions
    // -------------------------------------------
    private Manager getManager() throws IllegalAccessException {
        Account account = accountManagerService.getLoggedAccount();

        if (account == null || !(account instanceof Manager)) {
            throw new IllegalAccessException("The account does not have the required permissions");
        }

        return (Manager) account;
    }

    private void checkAccountType() throws IllegalAccessException {
        getManager();
    }
    // -------------------------------------------

    // Get all vehicles in the system - http://localhost:8080/api/vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // Get a specific vehicle by its ID - http://localhost:8080/api/vehicles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);

        if (vehicle.isPresent()) {
            return ResponseEntity.ok(vehicle.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add a new vehicle to the system - http://localhost:8080/api/vehicles
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            checkAccountType();

            Vehicle newVehicle = vehicleService.addVehicle(vehicle);
            return ResponseEntity.ok(newVehicle);
        } catch (IllegalAccessException exe) {
            return ResponseEntity.notFound().build();
        }
    }

 // Update an existing vehicle's details - http://localhost:8080/api/vehicles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        try {
            // Check if the account has the required permissions
            checkAccountType();

            // Validate the incoming vehicle details
            if (vehicleDetails.getState() == null || vehicleDetails.getState().getType() == null) {
                return ResponseEntity.badRequest().body("Invalid vehicle state: Missing or invalid 'type' field.");
            }
            System.out.println(vehicleDetails.getState());

            // Attempt to update the vehicle
            Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);

            if (updatedVehicle != null) {
                return ResponseEntity.ok(updatedVehicle);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalAccessException e) {
            // Handle insufficient permissions
            return ResponseEntity.status(403).body("You do not have permission to update this vehicle.");
        } catch (Exception e) {
            // Handle other errors
            return ResponseEntity.status(500).body("An error occurred while updating the vehicle: " + e.getMessage());
        }
    }


    // Delete a vehicle by its ID - http://localhost:8080/api/vehicles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            checkAccountType();

            vehicleService.deleteVehicle(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body(null);
        }
    }
}