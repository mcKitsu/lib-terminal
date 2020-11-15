package net.mckitsu.lib.terminal.command;

import net.mckitsu.lib.terminal.Terminal;
import net.mckitsu.lib.terminal.TerminalCommand;
import net.mckitsu.lib.terminal.TerminalLogger;

public class Stop implements TerminalCommand {
    private final Runnable onStop;
    private final Runnable terminalStop;

    public Stop(Runnable onStop, Runnable terminalStop){
        this.onStop = onStop;
        this.terminalStop = terminalStop;
    }

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
    public boolean handle(String[] args) {
        if(this.onStop != null)
            onStop.run();
        TerminalLogger.info("Service stopping...");
        if(this.terminalStop != null)
            this.terminalStop.run();

        TerminalLogger.info("Service stop!");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "stop";
    }
}
