package net.mckitsu.lib.terminal;

import net.mckitsu.lib.terminal.commands.CommandHelp;
import net.mckitsu.lib.terminal.commands.CommandReload;
import net.mckitsu.lib.terminal.commands.CommandRestart;
import net.mckitsu.lib.terminal.commands.CommandStop;

import java.util.Scanner;

public class Main {
    public static final String format = "%1$tF %1$tT [%4$s] %5$s%6$s%n";


    public static void main(String[] args){
        System.setProperty("java.util.logging.SimpleFormatter.format", format);

        Terminal terminal = new Terminal() {
            private Scanner scanner = new Scanner(System.in);

            @Override
            protected boolean onStart() {
                this.add(new CommandHelp(this.getCommands()));
                this.add(new CommandReload());
                this.add(new CommandRestart());
                this.add(new CommandStop());

                return true;
            }

            @Override
            protected String onRead() {
                return scanner.nextLine();
            }

            @Override
            protected void onUnknownCommand(String[] args) {
                getLogger().info(String.format("Unknown command '%s' - try 'help'\n", args[0]));
            }
        };

        terminal.start();


    }
}
