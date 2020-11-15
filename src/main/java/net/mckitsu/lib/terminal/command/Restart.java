package net.mckitsu.lib.terminal.command;

import net.mckitsu.lib.terminal.Terminal;
import net.mckitsu.lib.terminal.TerminalCommand;
import net.mckitsu.lib.terminal.TerminalLogger;

public class Restart implements TerminalCommand {
    private final Runnable onStop;
    private final Runnable onStart;
    private final Runnable onLoad;

    public Restart(Runnable onStart, Runnable onLoad, Runnable onStop){
        this.onStart = onStart;
        this.onStop = onStop;
        this.onLoad = onLoad;
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
        TerminalLogger.info("Service Stopping...");
        if(this.onStart != null)
            this.onStop.run();

        TerminalLogger.logger.info("Service Stop!");
        TerminalLogger.logger.info("Service starting...");

        if(this.onStart != null)
            onStart.run();

        if(this.onLoad != null)
            onLoad.run();

        TerminalLogger.logger.info("Service start!");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "restart";
    }
}
