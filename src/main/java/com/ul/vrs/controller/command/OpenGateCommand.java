package com.ul.vrs.controller.command;

import com.ul.vrs.service.GateService;

// Command to handle opening a gate.
public class OpenGateCommand implements Command {

    private GateService gateService; // Service to manage gate operations.

    // Initializes the command with the gate service.
    public OpenGateCommand(GateService gateService) {
        this.gateService = gateService;
    }

    // Executes the open gate operation.
    @Override
    public void execute() {
        gateService.openGate();
    }
}
