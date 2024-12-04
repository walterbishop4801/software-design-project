package com.ul.vrs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.factory.BookingDecoratorFactoryMethod;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;

import com.ul.vrs.repository.BookingRepository;

@Service
public class RentalSystemService {

    @Autowired 
    BookingRepository bookingRepository;

    private Map<UUID, Booking> bookings = new HashMap<>();

    public Optional<Booking> getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public UUID makeBooking(Customer customer, Vehicle vehicle) {
        if (vehicle != null) {
            Booking booking = new Booking(customer, vehicle);
            UUID bookingId = booking.getBookingId();

            bookingRepository.save(booking);
            // vehicle.updateState(VehicleState.RESERVED);

            return bookingId;
        }

        return null;
    }

    public Optional<Booking> customizeBooking(UUID bookingId, Customization customizationType) {
        Optional<Booking> b = bookingRepository.findById(bookingId);

        if(b.isPresent()) {
            Booking booking = BookingDecoratorFactoryMethod.createBookingDecorator(b.get(), customizationType);
            bookingRepository.save(booking);
            b = Optional.ofNullable(booking);
        };

        return b;
    }

    public void authenticateBookingPayment(UUID bookingId) {
        Booking b = bookings.get(bookingId);
        if (b != null)
            b.authenticatePayment();
    }

    // TODO: We gotta later use Mechanic to check it out to then update its state
    public void returnVehicle(UUID bookingId) {
        Optional<Booking> b = bookingRepository.findById(bookingId);
        if(b.isPresent()) {
            Booking booking = b.get();
            // Vehicle v = booking.getVehicle();
            // v.updateState(VehicleState.IN_MAINTENANCE);
            bookingRepository.delete(booking);
        }
    }

    public void cancelBooking(UUID bookingId) {
        Optional<Booking> b = bookingRepository.findById(bookingId);
        if(b.isPresent()) {
            Booking booking = b.get();
            // Vehicle v = booking.getVehicle();
            // v.updateState(VehicleState.AVAILABLE);
            bookingRepository.delete(booking);
        }
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}