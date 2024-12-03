package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.BookingDecorator;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingTest {

    private Customer testCustomer;
    private Vehicle testVehicle;
    private Booking testBooking;

    @BeforeAll
    void setup() {
        // Initialize common test objects
        testCustomer = new Customer("John Doe", "john.doe@example.com", "1234567890");

        testVehicle = new Vehicle(1L, "Camry", "Toyota", 2020, 25_000, Color.BLACK, new PetrolFuel(), VehicleState.AVAILABLE) {
            @Override
            public double getRentingCost(int numberOfRentingDays) {
                return 500.0 * numberOfRentingDays;
            }
        };

        testBooking = new Booking(testCustomer, testVehicle);
    }

    @BeforeEach
    void resetBooking() {
        // Reset testBooking for each test to ensure isolation
        testBooking = new Booking(testCustomer, testVehicle);
    }

    @Test
    void testValidBookingInitialization() {
        assertNotNull(testBooking.getBookingId(), "Booking ID should not be null");
        assertEquals(testCustomer, testBooking.getCustomer(), "Customer should be correctly initialized");
        assertEquals(testVehicle, testBooking.getVehicle(), "Vehicle should be correctly initialized");
        assertFalse(testBooking.getIsAuthenticated(), "is_authenticated should be false by default");
        assertEquals(10, testBooking.getPrice(), "Default price should be 10");
    }

    @Test
    void testSetIsAuthenticated() {
        assertFalse(testBooking.getIsAuthenticated(), "Payment should not be authenticated initially");
        testBooking.setIsAuthenticated(true);
        assertTrue(testBooking.getIsAuthenticated(), "Payment should be authenticated after setting it to true");
    }

    @Test
    void testBookingIdConsistency() {
        UUID bookingId = testBooking.getBookingId();
        assertNotNull(bookingId, "Booking ID should not be null");
        assertEquals(bookingId, testBooking.getBookingId(), "Booking ID should remain consistent");
    }

    @Test
    void testBookingDecoratorPriceDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(testBooking);
        assertEquals(testBooking.getPrice(), decoratedBooking.getPrice(), "Decorator should delegate getPrice to the original booking");
    }

    @Test
    void testBookingDecoratorCustomerDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(testBooking);
        assertEquals(testBooking.getCustomer(), decoratedBooking.getCustomer(), "Decorator should delegate getCustomer to the original booking");
    }

    @Test
    void testBookingDecoratorVehicleDelegation() {
        BookingDecorator decoratedBooking = new BookingDecorator(testBooking);
        assertEquals(testBooking.getVehicle(), decoratedBooking.getVehicle(), "Decorator should delegate getVehicle to the original booking");
    }

    @Test
    void testBookingDecoratorAuthenticatePayment() {
        BookingDecorator decoratedBooking = new BookingDecorator(testBooking);
        assertFalse(decoratedBooking.getIsAuthenticated(), "Decorator should initially report unauthenticated payment");
        decoratedBooking.setIsAuthenticated(true);
        assertTrue(decoratedBooking.getIsAuthenticated(), "Decorator should delegate payment authentication to the original booking");
    }
}
