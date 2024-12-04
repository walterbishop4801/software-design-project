package com.ul.vrs.command;

import java.util.UUID;

import com.ul.vrs.service.RentalSystemService;

// Command to handle returning a car in the rental system.
public class ReturnCarCommand implements Command {

    private RentalSystemService rentalSystemService; // Service to manage rental operations.
    private UUID bookingId; // ID of the booking to return.

    // Initializes the command with the rental service and booking ID.
    public ReturnCarCommand(RentalSystemService rentalSystemService, UUID bookingId) {
        this.rentalSystemService = rentalSystemService;
        this.bookingId = bookingId;
    }

    // Executes the return vehicle operation.
    @Override
    public void execute() {
        rentalSystemService.returnVehicle(bookingId);
    }
}
