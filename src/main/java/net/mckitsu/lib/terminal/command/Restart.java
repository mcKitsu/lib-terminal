package net.mckitsu.lib.terminal.command;

import net.mckitsu.lib.terminal.Terminal;
import net.mckitsu.lib.terminal.TerminalCommand;
import net.mckitsu.lib.terminal.TerminalLogger;

public class Restart implements TerminalCommand {
    private final Runnable restart;

    public Restart(Runnable restart){
        this.restart = restart;
    }

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
    public boolean handle(String[] args) {
        if(this.restart != null)
            restart.run();
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "restart";
    }
}
