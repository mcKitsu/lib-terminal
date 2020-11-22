package net.mckitsu.lib.terminal.commands;

import net.mckitsu.lib.terminal.Terminal;
import net.mckitsu.lib.terminal.TerminalCommand;

public class CommandStop implements TerminalCommand {

    @Override
    public String getCommand() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Stop service.";
    }

    @Override
    public String getHelp() {
        return String.format(" - %s\n", this.getDescription()) +
                String.format("   - %s", this.getCommandFormat());
    }

    @Override
    public boolean handle(Terminal terminal, String[] args) {
        terminal.getLogger().info("Stop");
        terminal.stop();
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "stop";
    }
}
