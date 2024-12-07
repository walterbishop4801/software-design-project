package com.ul.vrs.entity.account;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.GPSBookingDecorator;
import com.ul.vrs.entity.booking.decorator.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.decorator.VoucherBookingDecorator;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.fuel.DieselFuel;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.ReservedVehicleState;
import com.ul.vrs.interceptor.Interceptor;
import com.ul.vrs.service.VehicleManagerService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class CustomerTest {

	@Test
	public void testSearchAvailableVehicles() {
	    // Arrange
	    Customer customer = new Customer("Mark Bough", "password");

	    // Mock the VehicleManagerService
	    VehicleManagerService vehicleManagerService = mock(VehicleManagerService.class);
	    customer.setVehicleManagerService(vehicleManagerService); // Set the mocked service

	    // Mock the return value of getAllVehicles
	    List<Vehicle> vehicles = List.of(
	        new Car(1L, "Car A", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300),
	        new Car(2L, "Car B", "Honda", 2018, 18000, Color.RED, new PetrolFuel(), 4, 350)
	    );

	    // Set up the mock to return the list of vehicles
	    when(vehicleManagerService.getAllVehicles()).thenReturn(vehicles);

	    // Act
	    List<Vehicle> availableVehicles = customer.searchAvailableVehicles();

	    // Assert
	    assertNotNull(availableVehicles);
	    assertTrue(availableVehicles.size() > 0);
	}


    @Test
    public void testBookVehicle() {
        // Arrange
        Customer customer = new Customer("Mark Bough", "password");
        Vehicle vehicle = new Car(1L, "Test Car", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300);
        vehicle.updateState(new AvailableVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking booking = customer.bookVehicle(vehicle);

        // Assert
        assertNotNull(booking, "Booking should not be null.");
        assertTrue(vehicle.getState() instanceof ReservedVehicleState, "Vehicle should be in RESERVED state after booking.");
        verify(mockInterceptor).beforeAction(eq("bookVehicle"), eq(vehicle));
        verify(mockInterceptor).afterAction(eq("bookVehicle"), eq(booking));
    }

    @Test
    public void testCancelBooking() {
        // Arrange
        Customer customer = new Customer("Mark Bough", "password");
        Vehicle vehicle = new Car(1L, "Test Car", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300);
        vehicle.updateState(new ReservedVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        customer.cancelBooking(vehicle);

        // Assert
        assertTrue(vehicle.getState() instanceof AvailableVehicleState, "Vehicle should be in AVAILABLE state after cancellation.");
        verify(mockInterceptor).beforeAction(eq("cancelBooking"), eq(vehicle));
        verify(mockInterceptor).afterAction(eq("cancelBooking"), eq(vehicle));
    }

    @Test
    public void testReturnVehicle() {
        // Arrange
        Customer customer = new Customer("Mark Bough", "password");
        Vehicle vehicle = new Car(1L, "Test Car", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300);
        vehicle.updateState(new ReservedVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        customer.returnVehicle(vehicle);

        // Assert
        assertTrue(vehicle.getState() instanceof AvailableVehicleState, "Vehicle should be in AVAILABLE state after returning.");
        verify(mockInterceptor).beforeAction(eq("returnVehicle"), eq(vehicle));
        verify(mockInterceptor).afterAction(eq("returnVehicle"), eq(vehicle));
    }

    @Test
    public void testCustomizeBookingWithGPS() {
        // Arrange
        Customer customer = new Customer("Mark Bough", "password");
        Vehicle vehicle = new Car(1L, "Test Car", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300);
        vehicle.updateState(new AvailableVehicleState());

        // Create a booking first
        Booking booking = customer.bookVehicle(vehicle); // Ensure this does not return null

        // Check if booking was created successfully
        assertNotNull(booking, "Booking should not be null.");
        
        //Change vehicle state to reserved after booking is completed
        vehicle.updateState(new ReservedVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking customizedBooking = customer.customizeBooking(booking, Customization.GPS);

        // Assert
        assertNotNull(customizedBooking, "Customized booking should not be null.");
        assertTrue(customizedBooking instanceof GPSBookingDecorator, "Booking should be decorated with GPS customization.");
        verify(mockInterceptor).beforeAction(eq("applyGPS"), eq(booking));
        verify(mockInterceptor).afterAction(eq("applyGPS"), eq(customizedBooking));
    }
    
    @Test
    public void testCustomizeBookingWithInsurance() {
        // Arrange
        Customer customer = new Customer("Macht Bough", "securepassword");
        Vehicle vehicle = new Car(2L, "Luxury Car", "BMW", 2021, 50000, Color.BLACK, new DieselFuel(), 4, 400);
        vehicle.updateState(new AvailableVehicleState());

        // Create a booking first
        Booking booking = customer.bookVehicle(vehicle); // Ensure this does not return null

        // Check if booking was created successfully
        assertNotNull(booking, "Booking should not be null.");
        
        //Change vehicle state to reserved after booking is completed
        vehicle.updateState(new ReservedVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking customizedBooking = customer.customizeBooking(booking, Customization.INSURANCE);

        // Assert
        assertNotNull(customizedBooking, "Customized booking should not be null.");
        assertTrue(customizedBooking instanceof InsuranceBookingDecorator, "Booking should be decorated with Insurance customization.");
        verify(mockInterceptor).beforeAction(eq("applyInsurance"), eq(booking));
        verify(mockInterceptor).afterAction(eq("applyInsurance"), eq(customizedBooking));
    }

    @Test
    public void testCustomizeBookingWithVoucher() {
        // Arrange
        Customer customer = new Customer("Martha Bough", "safePassword");
        Vehicle vehicle = new Car(3L, "Economy Car", "Honda", 2019, 15000, Color.WHITE, new PetrolFuel(), 4, 250);
        vehicle.updateState(new AvailableVehicleState());

        // Create a booking first
        Booking booking = customer.bookVehicle(vehicle); // Ensure this does not return null

        // Check if booking was created successfully
        assertNotNull(booking, "Booking should not be null.");
        
        //Change vehicle state to reserved after booking is completed
        vehicle.updateState(new ReservedVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking customizedBooking = customer.customizeBooking(booking, Customization.VOUCHER);

        // Assert
        assertNotNull(customizedBooking, "Customized booking should not be null.");
        assertTrue(customizedBooking instanceof VoucherBookingDecorator, "Booking should be decorated with Voucher customization.");
        verify(mockInterceptor).beforeAction(eq("applyVoucher"), eq(booking));
        verify(mockInterceptor).afterAction(eq("applyVoucher"), eq(customizedBooking));
    }

}