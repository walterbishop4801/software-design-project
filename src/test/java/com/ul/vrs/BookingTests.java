package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private Customer customer;
    private Vehicle vehicle;
    private Booking booking;

    @BeforeEach
    void setUp() {
        // Mock Customer and Vehicle for testing
        customer = new Customer("John Doe", "john.doe@example.com", "1234567890");
        vehicle = new Vehicle(1L, "Camry", "Toyota", 2020, 25_000, Color.BLACK, new PetrolFuel(), VehicleState.AVAILABLE) {
            @Override
            public double getRentingCost() {
                return 500.0;
            }
        };
        booking = new Booking(customer, vehicle);
    }

    @Test
    void testBookingInitialization() {
        assertNotNull(booking.getBookingId(), "Booking ID should not be null");
        assertEquals(customer, booking.getCustomer(), "Customer should be correctly initialized");
        assertEquals(vehicle, booking.getVehicle(), "Vehicle should be correctly initialized");
        assertFalse(booking.getIsAuthenticated(), "is_authenticated should be false by default");
        assertEquals(10, booking.getPrice(), "Price should be initialized to 10");
    }

    @Test
    void testAuthenticatePayment() {
        assertFalse(booking.getIsAuthenticated(), "Payment should not be authenticated initially");
        booking.authenticatePayment();
        assertTrue(booking.getIsAuthenticated(), "Payment should be authenticated after calling authenticatePayment()");
    }

    @Test
    void testGetBookingId() {
        UUID bookingId = booking.getBookingId();
        assertNotNull(bookingId, "Booking ID should not be null");
        assertEquals(bookingId, booking.getBookingId(), "Booking ID should remain consistent");
    }

    @Test
    void testGetPrice() {
        assertEquals(10, booking.getPrice(), "Price should be initialized to 10");
    }

    @Test
    void testGetCustomer() {
        assertEquals(customer, booking.getCustomer(), "getCustomer() should return the correct customer");
    }

    @Test
    void testGetVehicle() {
        assertEquals(vehicle, booking.getVehicle(), "getVehicle() should return the correct vehicle");
    }
}
