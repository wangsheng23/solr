/**
 * 
 */
package com.wsheng.solr.query;

import java.util.List;

/**
 * Not Used currently 
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class QueryConditionFactory {

	public static ValueCondition and(String name, List<String> values) {
		return new ValueCondition(Operator.And, name, values);
	}
	
	public static ValueCondition or(String name, List<String> values) {
		return new ValueCondition(Operator.Or, name, values);
	}
	
	public static ValueCondition not(String name, List<String> values) {
		return new ValueCondition(Operator.Not, name, values);
	}
	
	public static ValueCondition minus(String name, List<String> values) {
		return new ValueCondition(Operator.Minus, name, values);
	}
	
	public static ValueCondition plus(String name, List<String> values) {
		return new ValueCondition(Operator.Plus, name, values);
	}
}
 