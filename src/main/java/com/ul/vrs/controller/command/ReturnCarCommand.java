package com.ul.vrs.controller.command;

import java.util.UUID;

import com.ul.vrs.service.RentalSystemService;

// Command to handle returning a car in the rental system.
public class ReturnCarCommand implements Command {

    private RentalSystemService rentalSystemService; // Service to manage rental operations.
    private UUID bookingId; // ID of the booking to return.

    // Initializes the command with the rental service and booking ID.
    public ReturnCarCommand(RentalSystemService rentalSystemService) {
        this.rentalSystemService = rentalSystemService;
    }

    public void setBookingID(UUID bookingId) {
        this.bookingId = bookingId;
    }

    // Executes the return vehicle operation.
    @Override
    public void execute() {
        if (bookingId != null) {
            rentalSystemService.returnVehicle(bookingId);
        }
    }
}
