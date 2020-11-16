package net.mckitsu.lib.terminal;

import net.mckitsu.lib.terminal.command.Help;
import net.mckitsu.lib.terminal.command.Reload;
import net.mckitsu.lib.terminal.command.Restart;
import net.mckitsu.lib.terminal.command.Stop;

import java.util.*;

/**
 * 終端機指令介面.
 *
 * @author  ZxyKira
 * @version 0.1.0
 */
public abstract class Terminal {
    public final Map<String, TerminalCommand> commandMap;

    private boolean isStart;
    private boolean isAsynchronous;
    private java.util.logging.Logger logger;
    private final TerminalRunnable terminalRunnable;

    /**
     * When service start finish after call this method.
     */
    protected abstract void onFinish();

    /**
     * When service start after call this method.
     */
    protected abstract boolean onStart();

    /**
     * When service load after call this method.
     */
    protected abstract void onLoad();

    /**
     * When service stop after call this method.
     */
    protected abstract void onStop();

    /**
     * Construction.
     *
     */
    public Terminal(){
        this.commandMap = new HashMap<>();
        this.isStart = false;
        this.isAsynchronous = false;
        this.add(new Help(this.commandMap));
        this.add(new Stop(this::onStop, this::terminalStop));
        this.add(new Restart(this::restart));
        this.add(new Reload(this::onLoad));
        this.terminalRunnable = new TerminalRunnable();
        this.logger = TerminalLogger.logger;
    }

    /**
     * Construction.
     *
     * @param logger system logger.
     */
    public Terminal(java.util.logging.Logger logger){
        this();
        this.logger = logger;
    }

    /**
     * Add a new command into {@link Terminal}
     *
     * @param command {@link TerminalCommand}
     */
    public void add(TerminalCommand command){
        this.commandMap.put(command.getCommand().toLowerCase(), command);
    }

    public TerminalCommand get(String command){
        return this.commandMap.get(command.toLowerCase());
    }

    /**
     * Remove a command in {@link Terminal}
     *
     * @param command {@link TerminalCommand}
     */
    public void remove(TerminalCommand command){
        this.commandMap.remove(command.getCommand().toLowerCase());
    }

    /**
     * Start the terminal service.
     */
    public void start(){
        if(this.isStart)
            return;

        this.isStart = true;

        if(this.isAsynchronous)
            new Thread(this.terminalRunnable);
        else
            this.terminalRunnable.run();
    }

    public void restart(){
        TerminalLogger.info("Service Stopping...");
        this.onStop();

        TerminalLogger.logger.info("Service Stop!");
        Terminal.this.logger.info("Service starting...");

        if(!onStart()){
            Terminal.this.logger.severe("Service start fail!");
            Terminal.this.logger.severe("Ending program.");
            terminalStop();
            return;
        }

        onLoad();
        Terminal.this.logger.info("Service start!");
        onFinish();
    }

    private void terminalStop(){
        this.isStart = false;
    }

    /**
     * Stop the terminal service.
     *
     */
    public void stop(){
        commandHandle("stop");
    }

    /**
     *
     * @param enable set service run as asynchronous.
     */
    public void setAsynchronous(boolean enable){
        if(!isStart)
            this.isAsynchronous = enable;
    }

    private void commandHandle(String command){
        String[] args = command.split(" ");

        boolean result = false;

        if(args.length == 0)
            return;

        TerminalCommand terminalCommand = get(args[0]);
        if(terminalCommand != null)
            result = terminalCommand.handle(args);

        if(!result){
            TerminalLogger.info("Unknown command '%s' - try 'help'\n", args[0]);
        }
    }

    private class TerminalRunnable implements Runnable{
        @Override
        public void run() {
            Terminal.this.logger.info("Service starting...");
            Scanner scanner = new Scanner(System.in);
            if(!onStart()){
                Terminal.this.logger.severe("Service start fail!");
                Terminal.this.logger.severe("Ending program.");
                return;
            }
            onLoad();
            Terminal.this.logger.info("Service start!");
            onFinish();


            while(isStart){
                String input = scanner.nextLine();
                commandHandle(input);
            }
        }
    }
}
