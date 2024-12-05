package com.ul.vrs.entity.account;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ul.vrs.entity.booking.payment.PaymentRequest;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.service.RentalSystemService;
import com.ul.vrs.service.VehicleManagerService;

// TODO: This class doesn't look like an entity, as it has methods already defined at AccountController
public class Customer extends Account {
    @Autowired
    private RentalSystemService rentalSystemService;

    @Autowired
    private VehicleManagerService vehicleManagerService;

<<<<<<< HEAD
    public Customer(String name, String id, String password) {
        super(name, id, password);
    }
    
    @Override
    public String getAccountType() {
        return "Customer";
    }    
    
    /* Check available vehicles */
    public void searchAvailableVehicles(VehicleManagerService service) {
    	
    	List<Vehicle> availableVehicles = new ArrayList<>();
    	for (Vehicle vehicle : service.getAllVehicles()) {
    	    if (vehicle.getState().equals(VehicleState.AVAILABLE)) {
    	        availableVehicles.add(vehicle);
    	    }
    	}
    	
    	if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles are currently available for booking.");
        } else {
            availableVehicles.forEach(vehicle -> 
                System.out.println("Vehicle: " + vehicle.getName() + 
                                   ", ID: " + vehicle.getID() + 
                                   " is available")
            );
        }
    }
    
    //Book a vehicle
    public Booking bookVehicle(Vehicle vehicle) {
    	long vehicleID = vehicle.getID();
    	String vehicleName = vehicle.getName();
    	if(vehicle.getState().equals(VehicleState.AVAILABLE)) {
    		System.out.println("Booking vehicle: " + vehicleName + ", " + vehicleID);
    		vehicle.updateState(VehicleState.RESERVED); 
        	return new Booking(this, vehicle);   			
		}
    	return null;
    }
    
    //Cancel a vehicle booking
    public void cancelBooking(Vehicle vehicle) {
    	long vehicleID = vehicle.getID();
    	String vehicleName = vehicle.getName();
    	System.out.println("Booking cancelled for vehicle: " + vehicleName + ", " + vehicleID);
    	vehicle.updateState(VehicleState.AVAILABLE);  			
    }
    
  //Return a vehicle 
    public void returnVehicle(Vehicle vehicle) {
    	long vehicleID = vehicle.getID();
    	String vehicleName = vehicle.getName();
    	System.out.println("Returning vehicle: " + vehicleName + ", " + vehicleID);
    	vehicle.updateState(VehicleState.AVAILABLE);  			
    }    

 // Customize a vehicle booking
    public Booking customizeBooking(Booking booking, Customization customization) {
        if (booking == null) {
            System.out.println("No booking found to customize.");
            return null;
        }

        Booking customizedBooking = null;

        if (customization == Customization.GPS) {
            System.out.println("Adding GPS to booking: " + booking.getBookingId());
            customizedBooking = new GPSBookingDecorator(booking);
        } else if (customization == Customization.INSURANCE) {
            System.out.println("Adding Insurance to booking: " + booking.getBookingId());
            customizedBooking = new InsuranceBookingDecorator(booking);
        } else if (customization == Customization.VOUCHER) {
            System.out.println("Applying Voucher to booking: " + booking.getBookingId());
            customizedBooking = new VoucherBookingDecorator(booking);
        } else {
            System.out.println("Invalid customization option.");
            return booking;
        }

        return customizedBooking;
=======
    public Customer(String username, String accountId, String password) {
        super(username, accountId, password);
>>>>>>> 98bca6413625c5104a624831d3c39ed72763800f
    }

    public List<Vehicle> searchAvailableVehicles() {
        return vehicleManagerService.getAllVehicles();
    }

    public void makeBooking(Vehicle vehicle, int numberOfRentingDays) {
        rentalSystemService.makeBooking(this, vehicle, numberOfRentingDays);
    }

    public void customizeVehicle(UUID booking) {
        rentalSystemService.customizeBooking(booking, null);
    }

    public void makeBookingPayment(UUID booking, PaymentRequest paymentRequest) {
        rentalSystemService.makeBookingPayment(booking, paymentRequest);
    }

    public void returnVehicle(UUID booking) {
        rentalSystemService.returnVehicle(booking);
    }

    public void cancelBooking(UUID vehicle) {
        rentalSystemService.cancelBooking(vehicle);
    }
}