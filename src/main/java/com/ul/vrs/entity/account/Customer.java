package com.ul.vrs.entity.account;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import com.ul.vrs.entity.booking.*;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.GPSBookingDecorator;
import com.ul.vrs.entity.booking.decorator.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.decorator.VoucherBookingDecorator;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.interceptor.Interceptor;

public class Customer extends Account {

    private List<Interceptor> interceptors = new ArrayList<>();

    public Customer(String name, String id, String password) {
        super(name, id, password);
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    private void runBeforeInterceptors(String action, Object target) {
        for (Interceptor interceptor : interceptors) {
            interceptor.beforeAction(action, target);
        }
    }

    private void runAfterInterceptors(String action, Object target) {
        for (Interceptor interceptor : interceptors) {
            interceptor.afterAction(action, target);
        }
    }

    @Override
    public String getAccountType() {
        return "Customer";
    }

    /* Check available vehicles */
    public void searchAvailableVehicles(List<Vehicle> vehicles) {
        runBeforeInterceptors("searchAvailableVehicles", vehicles);

        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getState().equals(VehicleState.AVAILABLE)) {
                availableVehicles.add(vehicle);
            }
        }

        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles are currently available for booking.");
        } else {
            availableVehicles.forEach(vehicle ->
                    System.out.println("Vehicle: " + vehicle.getName() +
                            ", ID: " + vehicle.getID() + " is available"));
        }

        runAfterInterceptors("searchAvailableVehicles", vehicles);
    }

    /* Book a vehicle */
    public Booking bookVehicle(Vehicle vehicle) {
        runBeforeInterceptors("bookVehicle", vehicle);

        if (vehicle.getState().equals(VehicleState.AVAILABLE)) {
            System.out.println("Booking vehicle: " + vehicle.getName() + ", ID: " + vehicle.getID());
            vehicle.updateState(VehicleState.RESERVED);
            Booking booking = new Booking(this, vehicle, 0);

            runAfterInterceptors("bookVehicle", booking);
            return booking;
        }

        System.out.println("Cannot book vehicle: " + vehicle.getName() + " is not available.");
        runAfterInterceptors("bookVehicle", vehicle);
        return null;
    }

    /* Cancel a vehicle booking */
    public void cancelBooking(Vehicle vehicle) {
        runBeforeInterceptors("cancelBooking", vehicle);

        System.out.println("Booking cancelled for vehicle: " + vehicle.getName() + ", ID: " + vehicle.getID());
        vehicle.updateState(VehicleState.AVAILABLE);

        runAfterInterceptors("cancelBooking", vehicle);
    }

    /* Return a vehicle */
    public void returnVehicle(Vehicle vehicle) {
        runBeforeInterceptors("returnVehicle", vehicle);

        System.out.println("Returning vehicle: " + vehicle.getName() + ", ID: " + vehicle.getID());
        vehicle.updateState(VehicleState.AVAILABLE);

        runAfterInterceptors("returnVehicle", vehicle);
    }

    private Booking applyCustomization(String action, Booking booking, Vehicle vehicle, Supplier<Booking> decorator) {
    	// Run interceptors before applying the customization
        runBeforeInterceptors(action, vehicle);

        // Apply the decorator to the booking
        Booking customizedBooking = decorator.get();

        // Run interceptors after applying the customization
        runAfterInterceptors(action, customizedBooking);

        // Return the customized booking
        return customizedBooking;
    }

    /* Customize a vehicle booking */
    public Booking customizeBooking(Booking booking, Customization customization) {
        runBeforeInterceptors("customizeBooking", booking);
        
     // Validate inputs
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null.");
        }

        Vehicle vehicle = booking.getVehicle();
        if (vehicle == null) {
            throw new IllegalArgumentException("Booking must have a valid vehicle.");
        }
        
     // Delegate to applyCustomization for specific customization logic
        switch (customization) {
            case GPS:
                return applyCustomization("applyGPS", booking, vehicle, () -> new GPSBookingDecorator(booking));
            case INSURANCE:
                return applyCustomization("applyInsurance", booking, vehicle, () -> new InsuranceBookingDecorator(booking));
            case VOUCHER:
                return applyCustomization("applyVoucher", booking, vehicle, () -> new VoucherBookingDecorator(booking));
            default:
                throw new IllegalArgumentException("Invalid customization option: " + customization);
        }
    }

    


}
