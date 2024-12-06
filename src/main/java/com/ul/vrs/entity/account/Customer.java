package com.ul.vrs.entity.account;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.decorator.Customization;
import com.ul.vrs.entity.booking.decorator.GPSBookingDecorator;
import com.ul.vrs.entity.booking.decorator.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.decorator.VoucherBookingDecorator;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.state.AvailableVehicleState;
import com.ul.vrs.entity.vehicle.state.ReservedVehicleState;
import com.ul.vrs.interceptor.Interceptor;
import com.ul.vrs.service.VehicleManagerService;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Customer extends Account {

    @Transient
    @Autowired
    private VehicleManagerService vehicleManagerService;

    private final List<Interceptor> interceptors = new ArrayList<>();

    public Customer(String name, String id, String password) {
        super(name, id, password);
    }

    public Customer() {
        super("test_username", "test_id", "test_password");
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
    
    public void setVehicleManagerService(VehicleManagerService vehicleManagerService) {
        this.vehicleManagerService = vehicleManagerService;
    }

    /* Search available vehicles */
    public List<Vehicle> searchAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        List<Vehicle> allVehicles = vehicleManagerService.getAllVehicles();

        runBeforeInterceptors("searchAvailableVehicles", allVehicles);

        for (Vehicle vehicle : allVehicles) {
            if (vehicle.getState() instanceof AvailableVehicleState) {
                availableVehicles.add(vehicle);
            }
        }

        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles are currently available for booking.");
        } else {
            availableVehicles.forEach(vehicle -> System.out.println("Vehicle: " + vehicle.getName() +
                    ", ID: " + vehicle.getID() + " is available."));
        }

        runAfterInterceptors("searchAvailableVehicles", availableVehicles);
        return availableVehicles;
    }

    /* Book a vehicle */
    public Booking bookVehicle(Vehicle vehicle) {
        runBeforeInterceptors("bookVehicle", vehicle);

        if (vehicle.getState() instanceof AvailableVehicleState) {
            System.out.println("Booking vehicle: " + vehicle.getName() + ", ID: " + vehicle.getID());
            vehicle.updateState(new ReservedVehicleState());
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
        vehicle.updateState(new AvailableVehicleState());

        runAfterInterceptors("cancelBooking", vehicle);
    }

    /* Return a vehicle */
    public void returnVehicle(Vehicle vehicle) {
        runBeforeInterceptors("returnVehicle", vehicle);

        System.out.println("Returning vehicle: " + vehicle.getName() + ", ID: " + vehicle.getID());
        vehicle.updateState(new AvailableVehicleState());

        runAfterInterceptors("returnVehicle", vehicle);
    }

    /* Apply customization logic using a decorator */
    private Booking applyCustomization(String action, Booking booking, Supplier<Booking> decorator) {
        runBeforeInterceptors(action, booking);

        Booking customizedBooking = decorator.get();

        runAfterInterceptors(action, customizedBooking);

        return customizedBooking;
    }

    /* Customize a vehicle booking */
    public Booking customizeBooking(Booking booking, Customization customization) {
        runBeforeInterceptors("customizeBooking", booking);

        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null.");
        }

        switch (customization) {
            case GPS:
                return applyCustomization("applyGPS", booking, () -> new GPSBookingDecorator(booking));
            case INSURANCE:
                return applyCustomization("applyInsurance", booking, () -> new InsuranceBookingDecorator(booking));
            case VOUCHER:
                return applyCustomization("applyVoucher", booking, () -> new VoucherBookingDecorator(booking));
            default:
                throw new IllegalArgumentException("Invalid customization option: " + customization);
        }
    }
}
