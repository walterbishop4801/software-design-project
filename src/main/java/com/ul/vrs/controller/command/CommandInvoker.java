package com.ul.vrs.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ul.vrs.service.GateService;
import com.ul.vrs.service.RentalSystemService;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component // Mark Invoker as a Spring-managed component
public class CommandInvoker {
    @Autowired
    private RentalSystemService rentalSystemService;

    @Autowired
    private GateService gateService;

    private final Map<String, Command> commands = new HashMap<>();

    // Initialize commands after dependencies are injected
    @PostConstruct
    public void initCommands() {
        commands.put("openGate", new OpenGateCommand(gateService));
        commands.put("returnCar", new ReturnCarCommand(rentalSystemService));
    }

    // Set a dynamic UUID for returnCar command before execution
    public void setBookingID(UUID bookingID) {
        if (bookingID != null) {
            ReturnCarCommand command = (ReturnCarCommand) commands.get("returnCar");
            command.setBookingID(bookingID);
        }
    }

    // Executes a specific command based on the key
    public void executeCommand(String key) {
        Command command = commands.get(key);

        if (command == null) {
            System.err.println("Unknown command for key=" + key);
            return;
        }

        command.execute();
    }
}