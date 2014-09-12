/**
 * 
 */
package com.wsheng.solr.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery.ORDER;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class QueryField<T extends Serializable> {

	private String name;
	
	/**Using this if the field has multiple values*/
	private List<T> values;
	
	private boolean facet;
	
	/** 1. The field won't highlight by default
	 *  2. WS: float，integer类型不能高亮
	 *  3. 需要加高亮的字段必须要在索引中的store=true才行
	 * 
	 */
	private boolean highlight;
	
	/** The field not the sort field by default*/
	private boolean sort;
	
	/** The sort type is asc by default*/
	private ORDER sortType = ORDER.asc;
	
	/** The field type is Normal by default*/
	private FieldType fieldType = FieldType.Normal;
	
	/** The field connector is null by default*/
	private FieldConnector fieldConnector;
	
	
	public final static String 			DELIMETER_COLON 	= ":";
	public final static String		 	DELIMETER_TO 		= "TO";
	public final static char 			DELIMETER_COMMA 	= ',';
	
	public QueryField(String name, List<T> values, boolean facet, boolean highlight, boolean sort, ORDER sortType, 
			FieldType fieldType, FieldConnector fieldConnector) {
		super();
		this.name 			= name;
		this.values 		= values;
		this.facet 			= facet;
		this.highlight 		= highlight;   
		this.sort 			= sort;
		this.sortType 		= sortType;
		this.fieldType 		= fieldType;
		this.fieldConnector = fieldConnector;
	}
	
	public QueryField(String name, T value, boolean facet, boolean highlight, boolean sort, ORDER sortType, 
			FieldType fieldType, FieldConnector fieldConnector) {
		super();
		this.name 			= name;
		this.facet 			= facet;
		
		List<T> values = new ArrayList<T>();
		values.add(value);
		this.values = values;
		
		this.highlight 		= highlight;
		this.sort 			= sort;
		this.sortType 		= sortType;
		this.fieldType 		= fieldType;
		this.fieldConnector = fieldConnector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<T> getValues() {
		return values;
	}

	public void setValues(List<T> values) {
		this.values = values;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
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


	public FieldConnector getFieldConnector() {
		return fieldConnector;
	}

	public void setFieldConnector(FieldConnector fieldConnector) {
		this.fieldConnector = fieldConnector;
	}


	public boolean isFacet() {
		return facet;
	}

	public void setFacet(boolean facet) {
		this.facet = facet;
	}



	public enum FieldType {
		Normal,
		Normal_Range,//includes Integer Range, Float Range, Double Range.
		Date_Range;
		
	}
	
	public enum FieldConnector {
		AND, OR, NOT, Minus, Plus;
	}
	
}
