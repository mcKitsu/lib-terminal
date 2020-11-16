package net.mckitsu.lib.terminal;

public class Main {
    public static void main(String[] args){
        Terminal terminal = new Terminal() {
            boolean tmp = true;

            @Override
            protected void onFinish() {
                System.out.println("onFinish");
                tmp = false;
            }

            @Override
            protected boolean onStart() {
                System.out.println("onStart");
                return tmp;
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
