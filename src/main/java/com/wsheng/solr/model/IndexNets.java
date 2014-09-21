/**
 * 
 */
package com.wsheng.solr.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**<p>IndexNets</p> persist bean standards for the Index pending to operate
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class IndexNets implements Serializable {

	private static final long serialVersionUID = -6673744631725001106L;

	/**
	 * The business id, could be user id, event id, circle id etc.
	 */
	private String bid; 
	
	/**
	 * The identify of the table name, it should be consistency with bid,
	 * typically, its the table name
	 */
	private String tid;
	
	/**
	 * The status for the row to index, 
	 * the value could be 'pending', 'success', 'failed'
	 */
	private String status;
	
	/**
	 * The indexed operating times to current row
	 */
	private String times;
	
	/**
	 * The priority for current row to be indexed.
	 */
	private int priority;
	
	/**
	 * the row latest updated time
	 */
	private Timestamp lastUpdate;
	
	/**
	 * Comments for current operation
	 */
	private String comments;

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
