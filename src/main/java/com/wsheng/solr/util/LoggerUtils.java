package com.wsheng.solr.util;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public class LoggerUtils {


    //  private static final String  LOG4J_PATH = "/log4j.properties";

    private static final String LOG_FLAG_DEBUG = " ----- ";
    private static final String LOG_FLAG_WARN = " ^^^^^ ";
    private static final String LOG_FLAG_INFO = " ***** ";
    private static final String LOG_FLAG_ERROR = " ##### ";
    private static final String LOG_FLAG_FATAL = " $$$$$ ";

//    static {
//        PropertyConfigurator.configure(LOG4J_PATH);
//    }


    public static void logCurrentMethod(Logger logger, Throwable t) {
        StackTraceElement ste = t.getStackTrace()[0];
        logger.info(ste.getMethodName());
    }

    public static void debugCurrentMethod(Logger logger, Throwable t) {
        StackTraceElement ste = t.getStackTrace()[0];
        logger.debug(ste.getMethodName());
    }

    public static void debug(Logger logger, String message) {
        logger.debug(LOG_FLAG_DEBUG + message + LOG_FLAG_DEBUG);
    }

    public static void warn(Logger logger, String message) {
        logger.warn(LOG_FLAG_WARN + message + LOG_FLAG_WARN);
    }

    public static void info(Logger logger, String message) {
        logger.info(LOG_FLAG_INFO + message + LOG_FLAG_INFO);
    }

    public static void error(Logger logger, String message) {
        logger.error(LOG_FLAG_ERROR + message + LOG_FLAG_ERROR);
    }

    public static void fatal(Logger logger, String message) {
        logger.fatal(LOG_FLAG_FATAL + message + LOG_FLAG_FATAL);
    }

    public static String getFullStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static void debug(Logger logger, Exception e) {
        logger.debug(LOG_FLAG_DEBUG + getFullStackTrace(e) + LOG_FLAG_DEBUG);
    }

    public static void warn(Logger logger, Exception e) {
        logger.warn(LOG_FLAG_WARN + getFullStackTrace(e) + LOG_FLAG_WARN);
    }

    public static void info(Logger logger, Exception e) {
        logger.info(LOG_FLAG_INFO + getFullStackTrace(e) + LOG_FLAG_INFO);
    }

    public static void error(Logger logger, Exception e) {
        logger.error(LOG_FLAG_ERROR + getFullStackTrace(e) + LOG_FLAG_ERROR);
    }

    public static void fatal(Logger logger, Exception e) {
        logger.fatal(LOG_FLAG_FATAL + getFullStackTrace(e) + LOG_FLAG_FATAL);
    }
}
