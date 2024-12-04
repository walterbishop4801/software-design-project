package com.ul.vrs.entity.booking;

import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.booking.decorator.*;
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
        testBooking = new Booking(testCustomer, testVehicle);
    }

    @Test
    void testValidBookingInitialization() {
        assertNotNull(testBooking.getBookingId());
        assertEquals(testCustomer, testBooking.getCustomer());
        assertEquals(testVehicle, testBooking.getVehicle());
        assertFalse(testBooking.getIsAuthenticated());
        assertEquals(10, testBooking.getPrice());
    }

    @Test
    void testGPSDecorator() {
        Booking decoratedBooking = new GPSBookingDecorator(testBooking);
        assertEquals(20, decoratedBooking.getPrice(), "GPS should add 10 to the price");
    }

    @Test
    void testInsuranceDecorator() {
        Booking decoratedBooking = new InsuranceBookingDecorator(testBooking);
        assertEquals(110, decoratedBooking.getPrice(), "Insurance should add 100 to the price");
    }

    @Test
    void testVoucherDecorator() {
        Booking decoratedBooking = new VoucherBookingDecorator(testBooking);
        assertEquals(0, decoratedBooking.getPrice(), "Voucher should subtract 10 from the price");
    }

    @Test
    void testCombinedDecorators() {
        Booking decoratedBooking = new GPSBookingDecorator(
            new InsuranceBookingDecorator(
                new VoucherBookingDecorator(testBooking)
            )
        );
        assertEquals(110, decoratedBooking.getPrice(), "Combined decorators should correctly calculate the total price");
    }

    @Test
    void testBookingDecoratorIdentity() {
        BookingDecorator gpsDecorator = new GPSBookingDecorator(testBooking);
        assertEquals(testBooking.getCustomer(), gpsDecorator.getCustomer());
        assertEquals(testBooking.getVehicle(), gpsDecorator.getVehicle());
        assertEquals(testBooking.getBookingId(), gpsDecorator.getBookingId());
    }

    @Test
    void testCustomizationEnum() {
        assertEquals(3, Customization.values().length, "There should be 3 customization options");
        assertTrue(Customization.valueOf("GPS") instanceof Customization, "GPS should be a valid customization");
        assertTrue(Customization.valueOf("INSURANCE") instanceof Customization, "INSURANCE should be a valid customization");
        assertTrue(Customization.valueOf("VOUCHER") instanceof Customization, "VOUCHER should be a valid customization");
    }

    @Test
    void testSetIsAuthenticated() {
        assertFalse(testBooking.getIsAuthenticated());
        testBooking.setIsAuthenticated(true);
        assertTrue(testBooking.getIsAuthenticated());
    }

    @Test
    void testBookingIdConsistency() {
        UUID bookingId = testBooking.getBookingId();
        assertNotNull(bookingId);
        assertEquals(bookingId, testBooking.getBookingId());
    }

    @Test
    void testDecoratorOrderDoesNotChangeBaseValues() {
        Booking gpsBooking = new GPSBookingDecorator(testBooking);
        Booking insuranceBooking = new InsuranceBookingDecorator(gpsBooking);
        assertEquals(20, gpsBooking.getPrice(), "GPS price should remain consistent even when wrapped in other decorators");
        assertEquals(120, insuranceBooking.getPrice(), "Insurance price should remain consistent when wrapping GPS");
    }

    @Test
    void testDecoratorChainingMaintainsIntegrity() {
        Booking voucherBooking = new VoucherBookingDecorator(testBooking);
        Booking gpsBooking = new GPSBookingDecorator(voucherBooking);
        assertEquals(10, gpsBooking.getPrice(), "Combined price should reflect the effects of both decorators");
    }

    @Test
    void testNullBookingForDecoratorThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPSBookingDecorator(null));
        assertNotNull(exception.getMessage());
    }

    @Test
    void testChainingDecoratorsWithSameType() {
        Booking doubleGPSBooking = new GPSBookingDecorator(new GPSBookingDecorator(testBooking));
        assertEquals(30, doubleGPSBooking.getPrice(), "Chaining GPS decorators should add price twice");
    }
}
