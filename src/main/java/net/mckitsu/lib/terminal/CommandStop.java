package net.mckitsu.lib.terminal;

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
        terminal.onStop();
        terminal.getLogger().info("Service stopping...");
        terminal.terminalStop();
        terminal.getLogger().info("Service stop!");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "stop";
    }
}
