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
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.factory.BookingDecoratorFactoryMethod;
import com.ul.vrs.entity.booking.payment.PayStrategy;
import com.ul.vrs.entity.booking.payment.PaymentRequest;
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

    public Booking customizeBooking(UUID bookingId, Customization customizationType) {
        Booking booking = bookings.get(bookingId);

        if (booking != null) {
            // Encapsulate booking decorator creation with Factory Method
            booking = BookingDecoratorFactoryMethod.createBookingDecorator(booking, customizationType);
            bookings.put(bookingId, booking);
            return booking;
        }

        return null;
    }

    public void makeBookingPayment(UUID bookingId, PaymentRequest paymentRequest) {
        Booking b = bookings.get(bookingId);
        PayStrategy strategy = paymentRequest.getPaymentStrategy();
        b.setIsAuthenticated(strategy.pay(b.getPrice()));
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