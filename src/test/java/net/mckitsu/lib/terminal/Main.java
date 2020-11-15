package net.mckitsu.lib.terminal;

public class Main {
    public static void main(String[] args){
        Terminal terminal = new Terminal() {
            @Override
            protected void onStart() {
                System.out.println("onStart");
            }

            @Override
            protected void onLoad() {
                System.out.println("onLoad");
                stop();
            }

            @Override
            protected void onStop() {
                System.out.println("onStop");
            }
        };

        terminal.setAsynchronous(false);
        terminal.start();


    }
}
