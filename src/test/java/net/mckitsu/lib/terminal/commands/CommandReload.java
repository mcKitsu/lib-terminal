package net.mckitsu.lib.terminal.commands;

import net.mckitsu.lib.terminal.Terminal;
import net.mckitsu.lib.terminal.TerminalCommand;

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
        terminal.getLogger().info("Reload");
        return true;
    }

    @Override
    public String getCommandFormat() {
        return "reload";
    }
}
