package net.mckitsu.lib.terminal;


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
        terminal.restart();
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "restart";
    }
}
