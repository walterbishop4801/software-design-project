package com.ul.vrs.entity.account;
/*
import org.junit.jupiter.api.Test;

import com.ul.vrs.entity.Color;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.GPSBookingDecorator;
import com.ul.vrs.entity.vehicle.Car;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.entity.vehicle.fuel.PetrolFuel;

import static org.junit.jupiter.api.Assertions.*;
*/
public class CustomerTest {
	/*@Test
	public void testCustomerCreation() {
		// Arrange
		String expectedName = "Shawn Sallins";
		String expectedId = "5613";
		String expectedPassword = "fdcg3vh561";

		//Act
		Customer customer = new Customer(expectedName, expectedId, expectedPassword);

		//Assert
		assertEquals(expectedName, customer.getUsername(), "Customer names should match in this case.");
		assertEquals(expectedId, customer.getAccountId(), "Customer ids should match in this case.");
		assertEquals(expectedPassword, customer.getPassword(), "Customer passwords should match in this case.");
	}

	@Test
	public void testEmptyName() {
		//Act
		Customer customer = new Customer("", "5613", "fdcg3vh561");

		//Assert
		assertEquals("", customer.getUsername(), "Customer shouldn't allow empty names");
	}

	@Test
	public void testNullName() {
		//Act
		Customer customer = new Customer(null, "5613", "fdcg3vh561");

		//Assert
		assertEquals(null, customer.getUsername(), "Customer shouldn't allow null names.");
	}

	@Test
	public void testCancelBooking() {
		Customer customer = new Customer("Rio Masakadza", "4565", "cvdgb15cd5");
		Vehicle vehicle = new Car(1L, "Scirocco", "VW", 2020, 26_000, Color.BLACK, new PetrolFuel(), 4, 425);

		customer.cancelBooking(vehicle);

		// Simulating cancellation logic (assume a status field in Booking)
		assertTrue(vehicle.getState().equals(VehicleState.AVAILABLE), "Booking should be marked as canceled");
	}

	@Test
	public void testReturnBooking() {
		Customer customer = new Customer("Rio Masakadza", "4565", "cvdgb15cd5");
		Vehicle vehicle = new Car(1L, "Scirocco", "VW", 2020, 26_000, Color.BLACK, new PetrolFuel(), 4, 425);

		customer.returnVehicle(vehicle);

		// Simulating cancellation logic (assume a status field in Booking)
		assertTrue(vehicle.getState().equals(VehicleState.AVAILABLE), "Booking should be marked as canceled");
	}

	@Test
	public void testCustomizeBooking() {
		// Arrange
		Customer customer = new Customer("Jay Masakadza", "4565", "cvdgb15cd5");
		Vehicle vehicle = new Car(1L, "Golf", "VW", 2020, 26_000, Color.BLACK, new PetrolFuel(), 4, 425);
		Booking booking = customer.bookVehicle(vehicle);

		// Act
		Booking customizedBooking = customer.customizeBooking(booking, Customization.GPS);

		// Assert
		assertNotNull(customizedBooking, "Customized booking should not be null");
		assertTrue(customizedBooking instanceof GPSBookingDecorator, "Booking should be decorated with GPS customization");
	}*/
}