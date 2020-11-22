package net.mckitsu.lib.terminal.commands;


import net.mckitsu.lib.terminal.Terminal;
import net.mckitsu.lib.terminal.TerminalCommand;

public class CommandRestart implements TerminalCommand {

    @Override
    public String getCommand() {
        return "restart";
    }

    @Override
    public String getDescription() {
        return "Restart service.";
    }

    @Override
    public String getHelp() {
        return String.format(" - %s\n", this.getDescription()) +
                String.format("   - %s", this.getCommandFormat());
    }

    @Override
    public boolean handle(Terminal terminal, String[] args) {
        terminal.getLogger().info("Restart");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "restart";
    }
}
