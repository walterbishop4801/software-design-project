package com.ul.vrs;


import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.booking.*;
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

    // Test: Booking Constructor with Valid Parameters
    @Test
    void testValidBookingInitialization() {
        assertNotNull(booking.getBookingId(), "Booking ID should not be null");
        assertEquals(customer, booking.getCustomer(), "Customer should be correctly initialized");
        assertEquals(vehicle, booking.getVehicle(), "Vehicle should be correctly initialized");
        assertFalse(booking.getIsAuthenticated(), "is_authenticated should be false by default");
        assertEquals(10, booking.getPrice(), "Price should be initialized to 10");
    }

    // Test: Booking Constructor with Null Customer
    @Test
    void testBookingInitializationWithNullCustomer() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(null, vehicle));
        assertEquals("Customer cannot be null", exception.getMessage(), "Constructor should throw an error for null customer");
    }

    // Test: Booking Constructor with Null Vehicle
    @Test
    void testBookingInitializationWithNullVehicle() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(customer, null));
        assertEquals("Vehicle cannot be null", exception.getMessage(), "Constructor should throw an error for null vehicle");
    }

    // Test: Authenticate Payment
    @Test
    void testAuthenticatePayment() {
        assertFalse(booking.getIsAuthenticated(), "Payment should not be authenticated initially");
        booking.authenticatePayment();
        assertTrue(booking.getIsAuthenticated(), "Payment should be authenticated after calling authenticatePayment()");
    }

    // Test: Booking ID Consistency
    @Test
    void testBookingIdConsistency() {
        UUID bookingId = booking.getBookingId();
        assertNotNull(bookingId, "Booking ID should not be null");
        assertEquals(bookingId, booking.getBookingId(), "Booking ID should remain consistent");
    }

    // Test: BookingDecorator with Null Booking
    @Test
    void testBookingDecoratorWithNullBooking() {
        Exception exception = assertThrows(NullPointerException.class, () -> new BookingDecorator(null));
        assertEquals("Booking cannot be null", exception.getMessage(), "Decorator should throw an error for null booking");
    }

    // Test: BookingDecorator Price Delegation
    @Test
    void testBookingDecoratorPriceDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertEquals(booking.getPrice(), decoratedBooking.getPrice(), "Decorator should delegate getPrice to the original booking");
    }

    // Test: BookingDecorator Customer Delegation
    @Test
    void testBookingDecoratorCustomerDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertEquals(booking.getCustomer(), decoratedBooking.getCustomer(), "Decorator should delegate getCustomer to the original booking");
    }

    // Test: BookingDecorator Vehicle Delegation
    @Test
    void testBookingDecoratorVehicleDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertEquals(booking.getVehicle(), decoratedBooking.getVehicle(), "Decorator should delegate getVehicle to the original booking");
    }

    // Test: BookingDecorator Payment Authentication
    @Test
    void testBookingDecoratorAuthenticatePayment() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertFalse(decoratedBooking.getIsAuthenticated(), "Decorator should initially report unauthenticated payment");
        decoratedBooking.authenticatePayment();
        assertTrue(decoratedBooking.getIsAuthenticated(), "Decorator should delegate payment authentication to the original booking");
    }

    // Test: BookingDecorator Override Behavior
    @Test
    void testBookingDecoratorOverrideBehavior() {
        class CustomBookingDecorator extends BookingDecorator {
            public CustomBookingDecorator(Booking booking) {
                super(booking);
            }

            @Override
            public long getPrice() {
                return super.getPrice() + 20; // Add a custom fee
            }
        }

        BookingDecorator customDecorator = new CustomBookingDecorator(booking);
        assertEquals(30, customDecorator.getPrice(), "Custom decorator should override getPrice and add 20");
    }
}
