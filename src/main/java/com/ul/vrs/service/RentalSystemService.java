package com.ul.vrs.service;

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
import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.booking.payment.strategy.PaymentStrategy;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;

import com.ul.vrs.repository.BookingRepository;

@Service
public class RentalSystemService {

    @Autowired 
    BookingRepository bookingRepository;

    @Autowired
    private VehicleManagerService vehicleManagerService;

    private Map<UUID, Booking> bookings = new HashMap<>();

    public Optional<Booking> getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public UUID makeBooking(Customer customer, Vehicle vehicle, int numberOfRentingDays) {
        if (vehicle != null) {
            Booking booking = new Booking(customer, vehicle, numberOfRentingDays);
            UUID bookingId = booking.getBookingId();

            bookingRepository.save(booking);
            vehicle.updateState(VehicleState.RESERVED);
            vehicleManagerService.updateVehicle(vehicle.getID(), vehicle);

            return bookingId;
        }

        return null;
    }

    public Optional<Booking> customizeBooking(UUID bookingId, Customization customizationType) {
        Optional<Booking> b = bookingRepository.findById(bookingId);

        if(b.isPresent()) {
            Booking booking = BookingDecoratorFactoryMethod.createBookingDecorator(b.get(), customizationType);
            bookingRepository.delete(b.get());
            bookingRepository.save(booking);
            b = Optional.ofNullable(booking);
        }

        return b;
    }

    public void makeBookingPayment(UUID bookingId, PaymentRequest paymentRequest) {
        Optional<Booking> b = bookingRepository.findById(bookingId);
        PaymentStrategy strategy = paymentRequest.getPaymentStrategy();
        if(b.isPresent()) {
            Booking booking = b.get();
            booking.setIsAuthenticated(strategy.pay(booking.getPrice()));
            bookingRepository.save(booking);
        }
    }

    // TODO: We gotta later use Mechanic to check it out to then update its state
    public void returnVehicle(UUID bookingId) {
        Optional<Booking> b = bookingRepository.findById(bookingId);
        if(b.isPresent()) {
            Booking booking = b.get();
            Vehicle v = booking.getVehicle();
            v.updateState(VehicleState.IN_MAINTENANCE);
            vehicleManagerService.updateVehicle(v.getID(), v);
            bookingRepository.delete(booking);
        }
    }

    public void cancelBooking(UUID bookingId) {
        Optional<Booking> b = bookingRepository.findById(bookingId);
        if(b.isPresent()) {
            Booking booking = b.get();
            Vehicle v = booking.getVehicle();
            v.updateState(VehicleState.AVAILABLE);
            vehicleManagerService.updateVehicle(v.getID(), v);
            bookingRepository.delete(booking);
        }
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}