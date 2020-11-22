package net.mckitsu.lib.terminal;

import java.util.Scanner;

public class Main {
    public static final String format = "%1$tF %1$tT [%4$s] %5$s%6$s%n";


    public static void main(String[] args){
        System.setProperty("java.util.logging.SimpleFormatter.format", format);

        Terminal terminal = new Terminal() {
            private Scanner scanner = new Scanner(System.in);

            @Override
            protected void onFinish() {
                getLogger().info("onFinish");
            }

            @Override
            protected boolean onStart() {
                getLogger().info("onStart");
                return true;
            }

            @Override
            protected void onLoad() {
                getLogger().info("onLoad");
            }

            @Override
            protected void onStop() {
                getLogger().info("onStop");
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
