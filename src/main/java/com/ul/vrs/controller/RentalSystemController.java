package com.ul.vrs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.BookingDecorator;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.RentalSystemService;
import com.ul.vrs.service.VehicleManagerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * RentalSystemController: controller of the rental system
 *
 * @author David Parreño (losedavidpb)
 * @version 2.0.1
 * @since 2.0.1
 */
@RestController
@RequestMapping("/api/renting")
public class RentalSystemController {

    @Autowired
    private RentalSystemService rentalSystemService;

    @Autowired
    private VehicleManagerService vehicleManager;

    //
    // getAvailableVehicles() has already been defined in VehicleManagerService
    //
    //

    /**
     * Make a new booking for current customer
     *
     * @param id id of the vehicle
     * @return new booking
     */
    // Make booking - http://localhost:8080/api/renting/make_booking/{id}
    @PostMapping("make_booking/{id}")
    public ResponseEntity<Booking> makeBooking(@PathVariable long idVehicle) {
        Optional<Vehicle> vehicleToBook = vehicleManager.getVehicleById(idVehicle);

        if (!vehicleToBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicle = vehicleToBook.get();
        Booking booking = rentalSystemService.makeBooking(vehicle);

        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Customize the booking with additional settings
     *
     * @param id id of the booking
     * @param decorator additional setting
     * @return HTML message
     */
    // Customise booking - http://localhost:8080/api/renting/customize_booking/{id}
    @PutMapping("customize_booking/{id}")
    public ResponseEntity<String> customizeBooking(@PathVariable long idBooking, @RequestBody BookingDecorator decorator) {
        Optional<Booking> bookingToCancel = rentalSystemService.getBookingById(idBooking);

        if (!bookingToCancel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingToCancel.get();
        rentalSystemService.customizeBooking(booking, decorator);

        return ResponseEntity.ok("Booking (ID=" + idBooking + ") has been customized.");
    }

    /**
     * Authenticate payment of the booking
     *
     * @param id id of the booking
     * @return HTML message
     */
    // Authenticate payment for booking - http://localhost:8080/api/renting/authenticate_payment/{id}
    @PutMapping("authenticate_payment/{id}")
    public ResponseEntity<String> authenticateBookingPayment(@PathVariable long idBooking) {
        Optional<Booking> bookingToCancel = rentalSystemService.getBookingById(idBooking);

        if (!bookingToCancel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingToCancel.get();
        rentalSystemService.authenticateBookingPayment(booking);

        return ResponseEntity.ok("Booking (ID=" + idBooking + ") has been authenticated.");
    }

    /**
     * Return vehicle of the booking
     *
     * @param id id of the booking
     * @return HTML message
     */
    // Return vehicle - http://localhost:8080/api/renting/return_vehicle/{id}
    @PutMapping("return_vehicle/{id}")
    public ResponseEntity<String> returnVehicle(@PathVariable long idBooking) {
        Optional<Booking> bookingToCancel = rentalSystemService.getBookingById(idBooking);

        if (!bookingToCancel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingToCancel.get();
        rentalSystemService.cancelBooking(booking);

        return ResponseEntity.ok("Vehicle of Booking (ID=" + idBooking + ") has been returned.");
    }

    /**
     * Cancel booking
     *
     * @param id id of the booking
     * @return HTML message
     */
    // Cancel booking - http://localhost:8080/api/renting/cancel_booking/{id}
    @DeleteMapping("cancel_booking/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable long idBooking) {
        Optional<Booking> bookingToCancel = rentalSystemService.getBookingById(idBooking);

        if (!bookingToCancel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingToCancel.get();
        rentalSystemService.cancelBooking(booking);

        return ResponseEntity.ok("Booking (ID=" + idBooking + ") has been canceled.");
    }
}