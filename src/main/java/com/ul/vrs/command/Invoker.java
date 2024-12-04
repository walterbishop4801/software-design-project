package com.ul.vrs.command;

import java.util.ArrayList;
import java.util.List;

// Invoker to store and execute a list of commands.
public class Invoker {

    private List<Command> commandList = new ArrayList<>(); // List to hold commands.

    // Adds a command to the list.
    public void addCommand(Command command) {
        commandList.add(command);
    }

    // Executes all stored commands and clears the list.
    public void executeCommands() {
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
