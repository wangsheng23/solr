/**
 * 
 */
package com.wsheng.solr.util;


import java.io.Serializable;
import java.util.List;


import com.wsheng.solr.query.QueryField;
import com.wsheng.solr.query.QueryField.FieldType;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class QueryUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable>  StringBuffer appendQuery(QueryField<T> field, StringBuffer paramsBuilder) {
		if (field.getValues() != null) {
			paramsBuilder.append(field.getName()).append(QueryField.DELIMETER_COLON);
			FieldType fieldType = field.getFieldType();
			
			switch (fieldType) {
			case Normal:
				paramsBuilder.append(ListUtils.listToString((List<String>) field.getValues(), QueryField.DELIMETER_COMMA));
				break;
			case Normal_Range:
				/*
				 * 1. <30: fq=field:[* TO 30}
				 * or fq=field:[0 TO 30}
				 * 
				 * 2. >= 30
				 * fq=field:[30 TO *}
				 */
				Object value1 = field.getValues().get(0), 
					value2 = field.getValues().get(1);
				
//				T firstValue = field.getValues().get(0);
//				T lastValue = field.getValues().get(1);
				if (value1 != null) {
					if (!Integer.class.isInstance(value1)
							&& !Float.class.isInstance(value1)
							&& !Double.class.isInstance(value1)) {
						throw new RuntimeException("Not supported type, only support Integer, Float, Double now!");
					}
				} else {
					value1 = "*";
				}
				
				if (value2 != null) {
					if (!Integer.class.isInstance(value2)
							&& !Float.class.isInstance(value2)
							&& !Double.class.isInstance(value2)) {
						throw new RuntimeException("Not supported type, only support Integer, Float, Double now!");
					}
				} else {
					value2 = "*";
				}
				 
				paramsBuilder.append("[").append(value1).append(" ").append(QueryField.DELIMETER_TO).
					append(" ").append(value2).append("]");
				
				break;
			case Date_Range:
				/*
				 * Note: I(Josh) have not tested this, But it should be OK
				 * fq=ptime:[2013-01-01T00:00:00Z TO * ]  
				 * 时间是2013年1月1号以后
				 */
				String date1 = field.getValues().get(0).toString(), 
					date2 = field.getValues().get(1).toString();
			
				date1 = date1 == null ? "*" : date1;
				date2 = date2 == null ? "*" : date2;
				
				paramsBuilder.append("[").append(date1).append(" ").append(QueryField.DELIMETER_TO).
					append(" ").append(date2).append("]");
				
				break;
			default:
				break;
			}
		}
		
		if (field.getFieldConnector() != null)
			paramsBuilder.append(" " + field.getFieldConnector().toString() + " ");
		
		return paramsBuilder;
	}
	
	
}
