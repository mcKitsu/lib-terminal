package net.mckitsu.lib.terminal;

import java.util.*;
import java.util.logging.Logger;

/**
 * 終端機指令介面.
 *
 * @author  ZxyKira
 */
public abstract class Terminal {
    private Map<String, TerminalCommand> commands;

    private boolean isStart;
    private java.util.logging.Logger logger;

    /* **************************************************************************************
     *  Abstract method
     */
    /**
     * When service start after call this method.
     */
    protected abstract boolean onStart();

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
        this(new HashMap<>(), java.util.logging.Logger.getGlobal());
    }

    public Terminal(Map<String, TerminalCommand> commands, Logger logger){
        this.isStart = false;
        this.commands = commands;
        this.logger = logger;
    }

    /**
     * Construction.
     *
     * @param logger system logger.
     */
    public Terminal(java.util.logging.Logger logger){
        this(new HashMap<>(), logger);
        this.logger = logger;
    }

    /* **************************************************************************************
     *  Public method
     */
    public Map<String, TerminalCommand> getCommands(){
        return this.commands;
    }

    public void executeCommand(String input){
        this.commandHandle(input);
    }

    /**
     * Add a new command into {@link Terminal}
     *
     * @param command {@link TerminalCommand}
     */
    public void add(TerminalCommand command){
        this.commands.put(command.getCommand().toLowerCase(), command);
    }

    public TerminalCommand get(String command){
        return this.commands.get(command.toLowerCase());
    }

    /**
     * Remove a command in {@link Terminal}
     *
     * @param command {@link TerminalCommand}
     */
    public void remove(TerminalCommand command){
        this.commands.remove(command.getCommand().toLowerCase());
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

    public Logger getLogger() {
        return logger;
    }

    /**
     * Stop the terminal service.
     *
     */
    public void stop(){
        this.isStart = false;
    }

    public String readLine(){
        return onRead();
    }

    /* **************************************************************************************
     *  protected method
     */

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
        onStart();
        while(isStart){
            commandHandle(onRead());
        }
    }
}
