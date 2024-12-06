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
        Vehicle newVehicle = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(newVehicle);
    }

 // Update an existing vehicle's details - http://localhost:8080/api/vehicles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        if (vehicleDetails.getState() == null || vehicleDetails.getState().getType() == null) {
            return ResponseEntity.badRequest().body("Invalid vehicle state: Missing or invalid 'type' field.");
        }
        System.out.println(vehicleDetails.getState());
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);

        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Delete a vehicle by its ID - http://localhost:8080/api/vehicles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {

        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();

    }
}