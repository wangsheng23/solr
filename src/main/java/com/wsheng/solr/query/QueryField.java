/**
 * 
 */
package com.wsheng.solr.query;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery.ORDER;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class QueryField {

	private String name;
	
	private List<String> values;
	
	/** The field won't highlight by default*/
	private boolean highLight;
	
	/** The field not the sort field by default*/
	private boolean sort;
	
	/** The sort type is asc by default*/
	private ORDER sortType = ORDER.asc;
	
	/** The field type is Normal by default*/
	private FieldType fieldType = FieldType.Normal;
	
	/** The field connector is null by default*/
	private FiledConnector fieldConnector;
	
	public final static String DELIMETER_COLON = ":";
	public final static String DELIMETER_TO = "TO";
	public final static char DELIMETER_COMMA = ',';
	
	public QueryField(String name, List<String> values, FiledConnector fieldConnector) {
		super();
		this.name = name;
		this.values = values;
		this.fieldConnector = fieldConnector;
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

	public boolean isHighLight() {
		return highLight;
	}

	public void setHighLight(boolean highLight) {
		this.highLight = highLight;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public ORDER getSortType() {
		return sortType;
	}

	public void setSortType(ORDER sortType) {
		this.sortType = sortType;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}


	public FiledConnector getFieldConnector() {
		return fieldConnector;
	}

	public void setFieldConnector(FiledConnector fieldConnector) {
		this.fieldConnector = fieldConnector;
	}



	public enum FieldType {
		Normal,
		Range;
		
	}
	
	public enum FiledConnector {
		AND, OR, NOT, Minus, Plus;
	}
	
}
