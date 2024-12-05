package com.ul.vrs.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.AccountManagerService;
import com.ul.vrs.service.RentalSystemService;
import com.ul.vrs.service.VehicleManagerService;
import com.ul.vrs.controller.command.CommandInvoker;
import com.ul.vrs.entity.account.Account;
import com.ul.vrs.entity.account.Customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/renting")
public class RentalSystemController {
    @Autowired
    private RentalSystemService rentalSystemService;

    @Autowired
    private VehicleManagerService vehicleManager;

    @Autowired
    private CommandInvoker invoker;

    @Autowired
    private AccountManagerService accountManagerService;

    // -------------------------------------------
    // Check user permissions
    // -------------------------------------------
    private Customer getCustomer() throws IllegalAccessException {
        Account account = accountManagerService.getLoggedAccount();

        if (account == null || !(account instanceof Customer)) {
            throw new IllegalAccessException("The account does not have the required permissions");
        }

        return (Customer) account;
    }

    private void checkAccountType() throws IllegalAccessException {
        getCustomer();
    }
    // -------------------------------------------

    // Get all vehicles - http://localhost:8080/api/vehicles
    @GetMapping("/list_bookings")
    public List<Booking> getBookings() {
        return rentalSystemService.getAllBookings();
    }

    // Make booking - http://localhost:8080/api/renting/make_booking/{id}
    @PostMapping("/make_booking/{id}")
    public ResponseEntity<?> makeBooking(@PathVariable long id, @RequestBody(required = false) Map<String, Integer> payload) {
        try {
            if (payload == null || !payload.containsKey("numberOfRentingDays")) {
                return ResponseEntity.badRequest().body("Invalid request: 'numberOfRentingDays' is required.");
            }

            int numberOfRentingDays = payload.get("numberOfRentingDays");
            Customer customer = getCustomer();

            Optional<Vehicle> vehicleToBook = vehicleManager.getVehicleById(id);

            if (!vehicleToBook.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Vehicle vehicle = vehicleToBook.get();
            UUID bookingId = rentalSystemService.makeBooking(customer, vehicle, numberOfRentingDays);

            if (bookingId != null) {
                return ResponseEntity.ok(bookingId);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body("Permission denied: You do not have access to make a booking.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Customise booking - http://localhost:8080/api/renting/customize_booking/{id}
    @PutMapping("/customize_booking/{id}")
    public ResponseEntity<Booking> customizeBooking(@PathVariable UUID id, @RequestBody Customization decorator) {
        try {
            checkAccountType();

            Optional<Booking> booking = rentalSystemService.customizeBooking(id, decorator);

            if(booking.isPresent()) {
                return ResponseEntity.ok(booking.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body(null);
        }
    }

    // Authenticate payment for booking - http://localhost:8080/api/renting/authenticate_payment/{id}
    @PutMapping("/make_payment/{id}")
    public ResponseEntity<Booking> makeBookingPayment(@PathVariable UUID id, @RequestBody PaymentRequest payment) {
        try {
            checkAccountType();

            rentalSystemService.makeBookingPayment(id, payment);
            Optional<Booking> booking = rentalSystemService.getBookingById(id);

            if (booking.isPresent()) {
                return ResponseEntity.ok(booking.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body(null);
        }
    }

    // Return vehicle - http://localhost:8080/api/renting/return_vehicle/{id}
    @PutMapping("/return_vehicle/{id}")
    public ResponseEntity<String> returnVehicle(@PathVariable UUID id) {
        try {
            checkAccountType();

            rentalSystemService.returnVehicle(id);

            return ResponseEntity.ok("Vehicle of Booking (ID=" + id + ") has been returned.");
        } catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body(null);
        }
    }

    // Cancel booking - http://localhost:8080/api/renting/cancel_booking/{id}
    @DeleteMapping("/cancel_booking/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable UUID id) {
        try {
            checkAccountType();

            rentalSystemService.cancelBooking(id);

            return ResponseEntity.ok("Booking (ID=" + id + ") has been canceled.");
        } catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body(null);
        }
    }

    // Return vehicle and open gate - http://localhost:8080/api/renting/return_vehicle_and_open_gate/{id}
    @PutMapping("/return_vehicle_and_open_gate/{id}")
    public ResponseEntity<String> returnVehicleAndOpenGate(@PathVariable UUID id) {
        try {
            checkAccountType();

            // Dynamically set the returnCar command with the current bookingId
            invoker.setBookingID(id);

            // Execute commands
            invoker.executeCommand("openGate");
            invoker.executeCommand("returnCar");

            return ResponseEntity.ok("Vehicle of Booking (ID=" + id + ") has been returned and gate opened.");
        }  catch (IllegalAccessException exe) {
            return ResponseEntity.status(403).body(null);
        }
    }
}