package com.ul.vrs.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.account.Customer;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.fuel.Fuel;

public class RentalSystemServiceTest {

    // Service being tested
    private RentalSystemService rentalSystemService;

    // Mock data for vehicles
    private List<Vehicle> mockVehicles;

    // Mock customer
    private Customer mockCustomer;

    @BeforeEach
    public void setup() {
        // Initialize the service and mock customer before each test
        rentalSystemService = new RentalSystemService();
        mockCustomer = new Customer("test_user", "test_id", "test_password");
        initMockVehicles(); // Initialize mock vehicles
    }

    private void initMockVehicles() {
        // Create mock implementations for Color and Fuel
        Color color = Color.BLACK; // Example color
        Fuel fuel = Mockito.mock(Fuel.class); // Mocking the Fuel interface
        Mockito.when(fuel.getCost()).thenReturn(1.5); // Mock fuel cost

        // Initialize mock vehicles with Car-specific parameters
        mockVehicles = new ArrayList<>();
        mockVehicles.add(new Car(1L, "TestCar1", "BrandA", 2020, 100.0, color, fuel, 4, 250.0f)); // 4 doors, small trunk
        mockVehicles.add(new Car(2L, "TestCar2", "BrandB", 2021, 200.0, color, fuel, 5, 350.0f)); // 5 doors, large trunk
    }

    @Test
    public void testMakeBooking_Success() {
        // Test for successfully making a booking
        Vehicle vehicle = mockVehicles.get(0); // Select the first vehicle
        UUID bookingId = rentalSystemService.makeBooking(mockCustomer, vehicle); // Create a booking

        assertNotNull(bookingId, "Booking ID should not be null"); // Booking ID must not be null
        Optional<Booking> booking = rentalSystemService.getBookingById(bookingId);
        assertTrue(booking.isPresent(), "Booking should exist"); // Booking should exist
        assertEquals(vehicle, booking.get().getVehicle(), "The vehicle in the booking should match the one provided");
        assertEquals(VehicleState.RESERVED, vehicle.getState(), "Vehicle should be RESERVED after booking");
    }

    @Test
    public void testMakeBooking_NullVehicle() {
        // Test for attempting to book a null vehicle
        UUID bookingId = rentalSystemService.makeBooking(mockCustomer, null);
        assertNull(bookingId, "Booking ID should be null when vehicle is null"); // Booking ID must be null
    }

    @Test
    public void testCustomizeBooking_Success() {
        // Test for successfully customizing a booking
        Vehicle vehicle = mockVehicles.get(0);
        UUID bookingId = rentalSystemService.makeBooking(mockCustomer, vehicle);

        // Use a valid customization (e.g., GPS)
        Customization mockCustomization = Customization.GPS;
        Booking customizedBooking = rentalSystemService.customizeBooking(bookingId, mockCustomization);

        assertNotNull(customizedBooking, "Customized booking should not be null"); // Ensure customization worked
        assertEquals(bookingId, customizedBooking.getBookingId(), "Booking ID should remain the same after customization");
    }

    @Test
    public void testCustomizeBooking_InvalidBooking() {
        // Test for customizing a non-existent booking
        UUID invalidBookingId = UUID.randomUUID(); // Generate a random UUID
        Customization mockCustomization = Customization.INSURANCE; // Example customization

        Booking customizedBooking = rentalSystemService.customizeBooking(invalidBookingId, mockCustomization);

        assertNull(customizedBooking, "Customization should fail for non-existent booking"); // Customization should fail
    }

    @Test
    public void testAuthenticateBookingPayment_Success() {
        // Test for successful payment authentication
        Vehicle vehicle = mockVehicles.get(0);
        UUID bookingId = rentalSystemService.makeBooking(mockCustomer, vehicle);

        rentalSystemService.authenticateBookingPayment(bookingId); // Authenticate payment
        Optional<Booking> booking = rentalSystemService.getBookingById(bookingId);

        assertTrue(booking.isPresent(), "Booking should exist");
        assertTrue(booking.get().getIsAuthenticated(), "Booking payment should be authenticated"); // Verify authentication
    }

    @Test
    public void testReturnVehicle_Success() {
        // Test for returning a vehicle
        Vehicle vehicle = mockVehicles.get(0);
        UUID bookingId = rentalSystemService.makeBooking(mockCustomer, vehicle);

        rentalSystemService.returnVehicle(bookingId); // Return the vehicle

        Optional<Booking> booking = rentalSystemService.getBookingById(bookingId);
        assertFalse(booking.isPresent(), "Booking should be removed after vehicle is returned"); // Booking should be removed
        assertEquals(VehicleState.IN_MAINTENANCE, vehicle.getState(), "Vehicle state should be IN_MAINTENANCE");
    }

    @Test
    public void testCancelBooking_Success() {
        // Test for canceling a booking
        Vehicle vehicle = mockVehicles.get(0);
        UUID bookingId = rentalSystemService.makeBooking(mockCustomer, vehicle);

        rentalSystemService.cancelBooking(bookingId); // Cancel the booking

        Optional<Booking> booking = rentalSystemService.getBookingById(bookingId);
        assertFalse(booking.isPresent(), "Booking should be removed after cancellation");
        assertEquals(VehicleState.AVAILABLE, vehicle.getState(), "Vehicle state should be AVAILABLE after cancellation");
    }

    @Test
    public void testGetAllBookings() {
        // Create bookings for all vehicles
        Map<Long, Vehicle> vehicleMap = new HashMap<>();
        for (Vehicle vehicle : mockVehicles) {
            UUID bookingId = rentalSystemService.makeBooking(mockCustomer, vehicle);
            vehicleMap.put(vehicle.getID(), vehicle); // Map vehicle IDs for comparison
        }

        // Retrieve all bookings
        List<Booking> allBookings = rentalSystemService.getAllBookings();

        // Assert that all bookings exist and match the expected vehicles
        assertEquals(mockVehicles.size(), allBookings.size(), "Should return all bookings");

        for (Booking booking : allBookings) {
            Vehicle expectedVehicle = vehicleMap.get(booking.getVehicle().getID());
            assertNotNull(expectedVehicle, "Booking vehicle should exist in mock data");
            assertEquals(expectedVehicle, booking.getVehicle(), "Booking should match the corresponding vehicle");
        }
    }


    @Test
    public void testCarRentingCost() {
        // Test for calculating the renting cost of a car
        Car car = (Car) mockVehicles.get(1); // A car with 5 doors and 350 trunk capacity
        int rentingDays = 5;
        double expectedCost = (car.getBaseCost() * rentingDays) + (1.5 * rentingDays) + (50 * rentingDays) + (30 * rentingDays);

        assertEquals(expectedCost, car.getRentingCost(rentingDays), "The renting cost calculation should match the expected value");
    }
}
