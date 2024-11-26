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

@Service
public class RentalSystemService {
    private Map<UUID, Booking> bookings = new HashMap<>();

    public Optional<Booking> getBookingById(UUID bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public UUID makeBooking(Customer customer, Vehicle vehicle) {
        if (vehicle != null) {
            Booking booking = new Booking(customer, vehicle);
            UUID bookingId = booking.getBookingId();

            bookings.put(bookingId, booking);
            vehicle.updateState(VehicleState.RESERVED);

            return bookingId;
        }

        return null;
    }

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

    public void authenticateBookingPayment(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null)
            b.authenticatePayment();
    }

    // TODO: We gotta later use Mechanic to check it out to then update its state
    public void returnVehicle(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null) {
            Vehicle v = b.getVehicle();
            v.updateState(VehicleState.IN_MAINTENANCE);
            bookings.remove(bookingId);
        }
    }

    public void cancelBooking(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null) {
            Vehicle v = b.getVehicle();
            v.updateState(VehicleState.AVAILABLE);
            bookings.remove(bookingId);
        }
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}