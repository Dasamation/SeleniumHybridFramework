package utils.logs;

import org.apache.logging.log4j.LogManager;

public class Logger {
    //Initialize Log4j instance
    private static final org.apache.logging.log4j.Logger logger =  LogManager.getLogger(Logger.class);
    //Info Level Logs
    public static void info (String message) {
        logger.info(message);
    }
    //Warn Level Logs
    public static void warn (String message) {
        logger.warn(message);
    }
    //Error Level Logs
    public static void error (String message, Exception e) {
        logger.error(message);
    }
    //Fatal Level Logs
    public static void fatal (String message) {
        logger.fatal(message);
    }
    //Debug Level Logs
    public static void debug (String message) {
        logger.debug(message);
    }
}