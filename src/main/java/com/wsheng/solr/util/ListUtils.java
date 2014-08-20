/**
 * 
 */
package com.wsheng.solr.util;

import java.util.List;


/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class ListUtils {

	/**
	 * example: output - > str1,str2,str3 ( delimiter is comma , )
	 */
	public static String listToString(List<String> list, char delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				sb.append(list.get(i));
			} else {
				sb.append(list.get(i)).append(delimiter);
			}
		}
		// return StringUtils.join(list.toArray(), delimiter);
		return sb.toString();
	}
}
