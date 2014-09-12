package com.wsheng.solr.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public class ExceptionUtils {
    public static String getStackTraceMsg(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
