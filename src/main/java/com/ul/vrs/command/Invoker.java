package com.ul.vrs.command;

import org.springframework.stereotype.Component;
import com.ul.vrs.service.GateService;
import com.ul.vrs.service.RentalSystemService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component // Mark Invoker as a Spring-managed component
public class Invoker {

    private final Map<String, Command> commandMap = new HashMap<>();

    private final GateService gateService;
    private final RentalSystemService rentalSystemService;

    // Constructor injection for services
    public Invoker(GateService gateService, RentalSystemService rentalSystemService) {
        System.out.println("Invoker instance created");
        this.gateService = gateService;
        this.rentalSystemService = rentalSystemService;
        prepareCommands();
    }

    // Prepare commands with proper services
    private void prepareCommands() {
        commandMap.put("openGate", new OpenGateCommand(gateService));
        commandMap.put("returnCar", null); 
    }

    // Set a dynamic UUID for returnCar command before execution
    public void setReturnCarCommand(UUID bookingId) {
        commandMap.put("returnCar", new ReturnCarCommand(rentalSystemService, bookingId));
    }

    // Executes a specific command based on the key
    public void executeCommand(String key) {
        Command command = commandMap.get(key);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Command not found for key: " + key);
        }
    }
}
