/**
 * 
 */
package com.wsheng.solr.query;

import java.util.List;

/**
 * Key Value Condition
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class ValueCondition extends Condition {
	
	private String name;
	
	private List<String> values;

	
	public ValueCondition(Operator oper) {
		super(oper);
	}

	public ValueCondition(Operator oper, String name, List<String> values) {
		super(oper);
		this.name = name;
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	
}
