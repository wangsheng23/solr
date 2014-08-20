/**
 * 
 */
package com.wsheng.solr.query;

/**
 * Not Used currently 
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public enum Operator {

	And("AND"),	Or("OR"), Not("NOT"), Minus("-"), Plus("+");
	
	private String expression;
	
	private Operator(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}


	
}
