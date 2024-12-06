package com.ul.vrs.entity.account;

import org.junit.jupiter.api.Test;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

public class CustomerTest {

    @Test
    public void testSearchAvailableVehicles() {
        // Arrange
        Customer customer = new Customer("John Doe", "123", "password");
        List<Vehicle> vehicles = List.of(
            new Car(1L, "Car A", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300),
            new Car(2L, "Car B", "Honda", 2018, 18000, Color.RED, new PetrolFuel(), 4, 350)
        );

        vehicles.get(0).updateState(new AvailableVehicleState());
        vehicles.get(1).updateState(new ReservedVehicleState());

        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        customer.searchAvailableVehicles();

        // Assert
        verify(mockInterceptor).beforeAction(eq("searchAvailableVehicles"), eq(vehicles));
        verify(mockInterceptor).afterAction(eq("searchAvailableVehicles"), eq(vehicles));
        assertTrue(vehicles.get(0).getState() instanceof AvailableVehicleState);
        assertTrue(vehicles.get(1).getState() instanceof ReservedVehicleState);
    }

    @Test
    public void testBookVehicle() {
        // Arrange
        Customer customer = new Customer("John Doe", "123", "password");
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
        Customer customer = new Customer("John Doe", "123", "password");
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
        Customer customer = new Customer("John Doe", "123", "password");
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
        Customer customer = new Customer("John Doe", "123", "password");
        Vehicle vehicle = new Car(1L, "Test Car", "Toyota", 2020, 20000, Color.BLUE, new PetrolFuel(), 4, 300);
        vehicle.updateState(new ReservedVehicleState());

        Booking booking = customer.bookVehicle(vehicle);
        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking customizedBooking = customer.customizeBooking(booking, Customization.GPS);

        // Assert
        assertNotNull(customizedBooking, "Customized booking should not be null.");
        assertTrue(customizedBooking instanceof GPSBookingDecorator, "Booking should be decorated with GPS customization.");
        verify(mockInterceptor).beforeAction(eq("applyGPS"), eq(vehicle));
        verify(mockInterceptor).afterAction(eq("applyGPS"), eq(customizedBooking));
    }

    @Test
    public void testCustomizeBookingWithInsurance() {
        // Arrange
        Customer customer = new Customer("Jane Doe", "456", "securepassword");
        Vehicle vehicle = new Car(2L, "Luxury Car", "BMW", 2021, 50000, Color.BLACK, new DieselFuel(), 4, 400);
        vehicle.updateState(new ReservedVehicleState());

        Booking booking = customer.bookVehicle(vehicle);
        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking customizedBooking = customer.customizeBooking(booking, Customization.INSURANCE);

        // Assert
        assertNotNull(customizedBooking, "Customized booking should not be null.");
        assertTrue(customizedBooking instanceof InsuranceBookingDecorator, "Booking should be decorated with Insurance customization.");
        verify(mockInterceptor).beforeAction(eq("applyInsurance"), eq(vehicle));
        verify(mockInterceptor).afterAction(eq("applyInsurance"), eq(customizedBooking));
    }

    @Test
    public void testCustomizeBookingWithVoucher() {
        // Arrange
        Customer customer = new Customer("Sam Doe", "789", "safePassword");
        Vehicle vehicle = new Car(3L, "Economy Car", "Honda", 2019, 15000, Color.WHITE, new PetrolFuel(), 4, 250);
        vehicle.updateState(new ReservedVehicleState());

        Booking booking = customer.bookVehicle(vehicle);
        Interceptor mockInterceptor = mock(Interceptor.class);
        customer.addInterceptor(mockInterceptor);

        // Act
        Booking customizedBooking = customer.customizeBooking(booking, Customization.VOUCHER);

        // Assert
        assertNotNull(customizedBooking, "Customized booking should not be null.");
        assertTrue(customizedBooking instanceof VoucherBookingDecorator, "Booking should be decorated with Voucher customization.");
        verify(mockInterceptor).beforeAction(eq("applyVoucher"), eq(vehicle));
        verify(mockInterceptor).afterAction(eq("applyVoucher"), eq(customizedBooking));
    }
}
