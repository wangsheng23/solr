/**
 *
 */
package com.wsheng.solr.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.Logger;


/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public class CommonsUtils {
	static Logger logger = Logger.getLogger(CommonsUtils.class);
    
	public final static String UTF8_CODE = "UTF-8";
	
	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, UTF8_CODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("Encode failed on: " + str);
			return str;
		}
	}
	
	public static String decode(String str) {
		try {
			return URLDecoder.decode(str, UTF8_CODE);
		} catch (UnsupportedEncodingException e) {
			logger.error("Decode failed on: " + str);
			return str;
		}
	}
	
}
