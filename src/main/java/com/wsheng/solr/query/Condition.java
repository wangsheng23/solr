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
public abstract class Condition {

	protected Operator oper;
	
	public Condition(Operator oper) {
		this.oper = oper;
	}
	
	public Condition() {
		super();
	}

	public Operator getOper() {
		return oper;
	}

	public void setOper(Operator oper) {
		this.oper = oper;
	}
	
	
}
