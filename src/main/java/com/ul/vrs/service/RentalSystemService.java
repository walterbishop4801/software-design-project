package com.ul.vrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.Customization;
import com.ul.vrs.entity.booking.GPSBookingDecorator;
import com.ul.vrs.entity.booking.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.VoucherBookingDecorator;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;

/**
 * RentalSystemService
 *
 * @author David Parreño (losedavidpb)
 * @version 2.3.2
 * @since 1.0.0
 */
@Service
public class RentalSystemService {
    // private final Customer customer;

    // Map to store bookings
    private Map<UUID, Booking> bookings = new HashMap<>();

    /**
     * Get booking based on its ID
     *
     * @param bookingId id of the booking
     * @return booking with passed ID
     */
    public Optional<Booking> getBookingById(UUID bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    /**
     * Make booking for passed vehicle
     *
     * @param v vehicle to book
     * @return booking
     */
    public UUID makeBooking(Customer customer, Vehicle vehicle) {
        if (vehicle != null && vehicle.checkAvailability()) {
            Booking booking = new Booking(customer, vehicle);
            UUID bookingId = booking.getBookingId();

            bookings.put(bookingId, booking);
            vehicle.updateState(VehicleState.RESERVED);

            return bookingId;
        }

        return null;
    }

    /**
     * Customize booking with passed decorator
     *
     * @param b booking to be customised
     * @param c decorator for the customisation
     */
    public Booking customizeBooking(UUID bookingId, Customization c) {
        Booking b = bookings.get(bookingId);

        if (b != null) {
            switch (c) {
                case GPS:
                    b = new GPSBookingDecorator(b);
                    break;
                case INSURANCE:
                    b = new InsuranceBookingDecorator(b);
                    break;
                case VOUCHER:
                    b = new VoucherBookingDecorator(b);
                    break;
                default:
                    break;
            }

            bookings.put(bookingId, b);
            return b;
        }

        return null;
    }

    /**
     * Authenticate booking payment
     *
     * @param b booking to be authenticated
     */
    public void authenticateBookingPayment(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null) b.authenticatePayment();
    }

    /**
     * Return vehicle of passed booking
     *
     * @param b booking
     */
    public void returnVehicle(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null) {
            Vehicle v = b.getVehicle();
            v.updateState(VehicleState.IN_MAINTENANCE);
            bookings.remove(bookingId);
        }
    }

    /**
     * Cancel passed booking
     *
     * @param b booking to be cancelled
     */
    public void cancelBooking(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null) {
            Vehicle v = b.getVehicle();
            v.updateState(VehicleState.AVAILABLE);
            bookings.remove(bookingId);
        }
    }

    /**
     * List all bookings
     *
     */
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}