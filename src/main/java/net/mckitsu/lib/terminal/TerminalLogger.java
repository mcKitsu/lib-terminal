package net.mckitsu.lib.terminal;

/**
 * Logger.
 *
 * @author  ZxyKira
 * @version 0.1.0
 * @since   1.4
 */
public class TerminalLogger {
    /**
     * Initialized logger.
     *
     */
    public static final java.util.logging.Logger logger = init();

    /**
     * Logger output format.
     *
     */
    public static final String format = "%1$tF %1$tT [%4$s] %5$s%6$s%n";

    private static java.util.logging.Logger init(){
        System.setProperty("java.util.logging.SimpleFormatter.format", format);
        return java.util.logging.Logger.getGlobal();
    }

    /**
     * Info log.
     *
     * @param log Message.
     */
    public static void info(String log){
        logger.info(log);
    }

    /**
     * Info log
     *
     * @param format Format.
     * @param args Argument.
     */
    public static void info(String format, Object... args){
        String log = String.format(format, args);
        logger.info(log);
    }

    /**
     * Warning log
     *
     * @param log Message.
     */
    public static void warning(String log){
        logger.warning(log);
    }

    /**
     * Warning log
     *
     * @param format Format.
     * @param args Argument.
     */
    public static void warning(String format, Object... args){
        String log = String.format(format, args);
        logger.warning(log);
    }

    /**
     * Severe log
     *
     * @param log Message.
     */
    public static void severe(String log){
        logger.severe(log);
    }

    /**
     * Severe log
     *
     * @param format Format.
     * @param args Argument.
     */
    public static void severe(String format, Object... args){
        String log = String.format(format, args);
        logger.severe(log);
    }

}
