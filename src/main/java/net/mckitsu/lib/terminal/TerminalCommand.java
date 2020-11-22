package net.mckitsu.lib.terminal;


/**
 * Terminal Command Interface.
 *
 * @author  ZxyKira
 */
public interface TerminalCommand {
    /**
     * Get command as string.
     *
     * @return This command.
     */
    String getCommand();

    /**
     * Get command description.
     *
     * @return This command description.
     */
    String getDescription();

    /**
     * Show how to use this command.
     *
     * @return Description.
     */
    String getHelp();

    /**
     * When terminal input match this command will do this method.
     *
     * @param args Input arguments.
     * @return Command help information;
     */
    boolean handle(Terminal terminal, String[] args);

    /**
     * This is a read only string, it will show this string when user input help.
     * <p>example: COMMAND [args0]</p>
     *
     * @return Command format;
     */
    String getCommandFormat();
}
