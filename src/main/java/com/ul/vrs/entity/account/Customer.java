package com.ul.vrs.entity.account;

import java.util.ArrayList;
import java.util.List;
import com.ul.vrs.entity.booking.Booking;
import com.ul.vrs.entity.booking.Customization;
import com.ul.vrs.entity.booking.GPSBookingDecorator;
import com.ul.vrs.entity.booking.InsuranceBookingDecorator;
import com.ul.vrs.entity.booking.VoucherBookingDecorator;
import com.ul.vrs.entity.vehicle.Vehicle;
import com.ul.vrs.entity.vehicle.VehicleState;
import com.ul.vrs.service.VehicleManagerService;


public class Customer extends Account{

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
    }


}
