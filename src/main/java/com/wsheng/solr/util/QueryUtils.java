/**
 * 
 */
package com.wsheng.solr.util;


import com.wsheng.solr.query.QueryField;
import com.wsheng.solr.query.QueryField.FieldType;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class QueryUtils {

	public static StringBuilder appendQuery(QueryField field, StringBuilder paramsBuilder) {
		if (field.getValues() != null) {
			paramsBuilder.append(field.getName()).append(QueryField.DELIMETER_COLON);
			FieldType fieldType = field.getFieldType();
			
			switch (fieldType) {
			case Normal:
				paramsBuilder.append(ListUtils.listToString(field.getValues(), QueryField.DELIMETER_COMMA));
				break;
			case Range:
				if (field.getValues().size() != 2)
					throw new RuntimeException("There are should 2 values for Range Field(From Value and To Value)");
				
				int fromValue = Integer.parseInt(field.getValues().get(0));
				int toValue = Integer.parseInt(field.getValues().get(1));
				fromValue = fromValue <= toValue ? fromValue : toValue; 
				
				paramsBuilder.append("[").append(fromValue).append(QueryField.DELIMETER_TO).
					append(toValue).append("]");
				
				break;
			}
		}
		
		if (field.getFieldConnector() != null)
			paramsBuilder.append(" " +field.getFieldConnector().toString() + " ");
		
		return paramsBuilder;
	}
	
}
