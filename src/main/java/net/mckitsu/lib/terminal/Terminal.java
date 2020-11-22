package net.mckitsu.lib.terminal;

import java.util.*;
import java.util.logging.Logger;

/**
 * 終端機指令介面.
 *
 * @author  ZxyKira
 */
public abstract class Terminal {
    public final Map<String, TerminalCommand> commandMap;

    private boolean isStart;
    private java.util.logging.Logger logger;

    /* **************************************************************************************
     *  Abstract method
     */

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

    protected abstract String onRead();

    protected abstract void onUnknownCommand(String[] args);

    /* **************************************************************************************
     *  Construct method
     */

    /**
     * Construction.
     *
     */
    public Terminal(){
        this.commandMap = new HashMap<>();
        this.isStart = false;
        this.add(new CommandHelp(this.commandMap));
        this.add(new CommandStop());
        this.add(new CommandRestart());
        this.add(new CommandReload());
        this.logger = java.util.logging.Logger.getGlobal();
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

    /* **************************************************************************************
     *  Public method
     */

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

        this.terminalRunnable();
    }

    public void restart(){
        this.logger.info("Service Stopping...");
        this.onStop();

        this.logger.info("Service Stop!");
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

    public Logger getLogger() {
        return logger;
    }

    /**
     * Stop the terminal service.
     *
     */
    public void stop(){
        commandHandle("stop");
    }

    public String readLine(){
        return onRead();
    }

    /* **************************************************************************************
     *  protected method
     */

    protected void terminalStop(){
        this.isStart = false;
    }

    /* **************************************************************************************
     *  Private method
     */

    private void commandHandle(String command){
        String[] args = command.split(" ");

        boolean result = false;

        if(args.length == 0)
            return;

        TerminalCommand terminalCommand = get(args[0]);
        if(terminalCommand != null)
            result = terminalCommand.handle(this, args);

        if(!result){
            this.onUnknownCommand(args);
        }
    }

    private void terminalRunnable(){
        Terminal.this.logger.info("Service starting...");

        if(!onStart()){
            Terminal.this.logger.severe("Service start fail!");
            Terminal.this.logger.severe("Ending program.");
            return;
        }
        onLoad();
        Terminal.this.logger.info("Service start!");
        onFinish();


        while(isStart){
            commandHandle(onRead());
        }
    }
}
