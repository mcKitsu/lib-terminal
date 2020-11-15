package net.mckitsu.lib.terminal.command;

import net.mckitsu.lib.terminal.TerminalLogger;
import net.mckitsu.lib.terminal.TerminalCommand;

public class Reload implements TerminalCommand {
    protected final Runnable onReload;

    public Reload(Runnable onReload){
        this.onReload = onReload;
    }


    @Override
    public String getCommand() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloading resource config.";
    }

    @Override
    public String getHelp() {
        return String.format(" - %s\n", this.getDescription()) +
                String.format("   - %s", this.getCommandFormat());
    }

    @Override
    public boolean handle(String[] args) {
        TerminalLogger.info("Service reloading...");
        this.onReload.run();
        TerminalLogger.info("Service reload!");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "reload";
    }
}
