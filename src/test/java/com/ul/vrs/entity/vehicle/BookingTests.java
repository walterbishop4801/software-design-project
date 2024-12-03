package com.ul.vrs.entity.vehicle;

import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.booking.*;
import com.ul.vrs.entity.booking.decorator.BookingDecorator;
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
        customer = new Customer("John Doe", "john.doe@example.com", "1234567890");

        vehicle = new Vehicle(1L, "Camry", "Toyota", 2020, 25_000, Color.BLACK, new PetrolFuel(), VehicleState.AVAILABLE) {
            @Override
            public double getRentingCost(int numberOfRentingDays) {
                return 500.0 * numberOfRentingDays;
            }
        };

        booking = new Booking(customer, vehicle);
    }

    @Test
    void testValidBookingInitialization() {
        assertNotNull(booking.getBookingId(), "Booking ID should not be null");
        assertEquals(customer, booking.getCustomer(), "Customer should be correctly initialized");
        assertEquals(vehicle, booking.getVehicle(), "Vehicle should be correctly initialized");
        assertFalse(booking.getIsAuthenticated(), "is_authenticated should be false by default");
        assertEquals(10, booking.getPrice(), "Price should be initialized to 10");
    }

    @Test
    void testBookingInitializationWithNullCustomer() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(null, vehicle));
        assertEquals("Customer cannot be null", exception.getMessage());
    }

    @Test
    void testBookingInitializationWithNullVehicle() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Booking(customer, null));
        assertEquals("Vehicle cannot be null", exception.getMessage());
    }

    @Test
    void testAuthenticatePayment() {
        assertFalse(booking.getIsAuthenticated(), "Payment should not be authenticated initially");
        booking.setIsAuthenticated(true); // Using setIsAuthenticated
        assertTrue(booking.getIsAuthenticated(), "Payment should be authenticated after setting it to true");
    }

    @Test
    void testBookingIdConsistency() {
        UUID bookingId = booking.getBookingId();
        assertNotNull(bookingId, "Booking ID should not be null");
        assertEquals(bookingId, booking.getBookingId(), "Booking ID should remain consistent");
    }

    @Test
    void testBookingDecoratorWithNullBooking() {
        Exception exception = assertThrows(NullPointerException.class, () -> new BookingDecorator(null));
        assertEquals("Booking cannot be null", exception.getMessage());
    }

    @Test
    void testBookingDecoratorPriceDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertEquals(booking.getPrice(), decoratedBooking.getPrice());
    }

    @Test
    void testBookingDecoratorCustomerDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertEquals(booking.getCustomer(), decoratedBooking.getCustomer());
    }

    @Test
    void testBookingDecoratorVehicleDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertEquals(booking.getVehicle(), decoratedBooking.getVehicle());
    }

    @Test
    void testBookingDecoratorAuthenticatePayment() {
        BookingDecorator decoratedBooking = new BookingDecorator(booking);
        assertFalse(decoratedBooking.getIsAuthenticated(), "Decorator should initially report unauthenticated payment");
        decoratedBooking.setIsAuthenticated(true); // Using setIsAuthenticated
        assertTrue(decoratedBooking.getIsAuthenticated(), "Decorator should delegate payment authentication to the original booking");
    }

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
        assertEquals(30, customDecorator.getPrice());
    }
}
