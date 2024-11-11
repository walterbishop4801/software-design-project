package com.ul.vrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ul.vrs.entity.Customer;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.BookingDecorator;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;

/**
 * RentalSystemService
 *
 * @author David Parreño (losedavidpb)
 * @version 2.1.0
 * @since 1.0.0
 */
@Service
public class RentalSystemService {
    private final Customer customer;

    /**
     * Creates new instance of RentalSystemService
     *
     * @param customer current customer
     */
    public RentalSystemService(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get booking based on its ID
     *
     * @param idBooking id of the booking
     * @return booking with passed ID
     */
    public Optional<Booking> getBookingById(long idBooking) {
        return Optional.ofNullable(customer.getBooking(idBooking));
    }

    /**
     * Make booking for passed vehicle
     *
     * @param v vehicle to book
     * @return booking
     */
    public Booking makeBooking(Vehicle v) {
        if (v != null) {
            if (v.checkAvailability()) {
                Booking booking = new Booking(customer, v);

                if (!customer.containsBooking(booking)) {
                    customer.addBooking(booking);
                    return booking;
                }
            }
        }

        return null;
    }

    /**
     * Customize booking with passed decorator
     *
     * @param b booking to be customised
     * @param decorator decorator for the customisation
     */
    public void customizeBooking(Booking b, BookingDecorator decorator) {
        if (b != null && decorator != null) {
            decorator.setBooking(b);
            decorator.customise();
        }
    }

    /**
     * Authenticate booking payment
     *
     * @param b booking to be authenticated
     */
    public void authenticateBookingPayment(Booking b) {
        if (b != null) b.authenticatePayment();
    }

    /**
     * Return vehicle of passed booking
     *
     * @param b booking
     */
    public void returnVehicle(Booking b) {
        customer.removeBooking(b);
    }

    /**
     * Cancel passed booking
     *
     * @param b booking to be cancelled
     */
    public void cancelBooking(Booking b) {
        returnVehicle(b);
    }
}