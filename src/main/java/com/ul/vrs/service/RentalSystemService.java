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
import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.booking.payment.strategy.PaymentStrategy;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.InMaintenanceVehicleState;
import com.ul.vrs.entity.vehicle.state.ReservedVehicleState;

@Service
public class RentalSystemService {
    private Map<UUID, Booking> bookings = new HashMap<>();

    public Optional<Booking> getBookingById(UUID bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public UUID makeBooking(Customer customer, Vehicle vehicle, int numberOfRentingDays) {
        if (vehicle != null) {
            Booking booking = new Booking(customer, vehicle, numberOfRentingDays);
            UUID bookingId = booking.getBookingId();

            bookings.put(bookingId, booking);
            vehicle.updateState(new ReservedVehicleState());

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
        PaymentStrategy strategy = paymentRequest.getPaymentStrategy();
        b.setIsAuthenticated(strategy.pay(b.getPrice()));
    }

    // TODO: We gotta later use Mechanic to check it out to then update its state
    public void returnVehicle(UUID bookingId) {
        Booking b = bookings.get(bookingId);

        if (b != null) {
            Vehicle v = b.getVehicle();
            v.updateState(new InMaintenanceVehicleState());
            bookings.remove(bookingId);
        }
    }

    public void cancelBooking(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null) {
            Vehicle v = b.getVehicle();
            v.updateState(new AvailableVehicleState());
            bookings.remove(bookingId);
        }
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}