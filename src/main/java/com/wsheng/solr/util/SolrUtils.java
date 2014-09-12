/**
 * 
 */
package com.wsheng.solr.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrUtils {

	
	public static Object getValue(SolrDocument doc, String fieldName) {
		Collection<String> fieldNames = doc.getFieldNames();
		
		for (String field : fieldNames) {
			if (fieldName.equalsIgnoreCase(field)) {
				return doc.getFieldValue(fieldName);
			}
		}
		
		return null;
	}
	
	public static Set<Object> getValues(SolrDocumentList docs, String fieldName) {
		if (docs != null && docs.size() > 0) {
			Set<Object> values = new HashSet<Object>();
			
			for (SolrDocument doc : docs) {
				Collection<String> fieldNames = doc.getFieldNames();
				
				for (String field : fieldNames) {
					if (fieldName.equalsIgnoreCase(field)) {
						values.add(doc.getFieldValue(fieldName));
						break;
					}
				}
			}
			
			return values;
		}
		
		return null;
		
	}
	
	public static List<FacetField> getFacetField(QueryResponse response) {
		return response.getFacetFields();
	}
	
	/**
	 * 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名  
	 * @param response
	 * @return
	 */
	public static Map<String, Map<String, List<String>>> getHighlighFields(QueryResponse response) {
		return response.getHighlighting();
	}
	
}
