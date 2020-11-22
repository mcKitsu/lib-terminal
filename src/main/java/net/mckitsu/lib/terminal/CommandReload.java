package net.mckitsu.lib.terminal;

public class CommandReload implements TerminalCommand {
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
    public boolean handle(Terminal terminal, String[] args) {
        terminal.getLogger().info("Service reloading...");
        terminal.onLoad();
        terminal.getLogger().info("Service reload!");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "reload";
    }
}
