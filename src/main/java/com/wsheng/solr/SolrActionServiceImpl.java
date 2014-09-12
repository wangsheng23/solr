/**
 * 
 */
package com.wsheng.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import com.wsheng.solr.query.QueryField;
import com.wsheng.solr.util.CommonsUtils;
import com.wsheng.solr.util.ExceptionUtils;
import com.wsheng.solr.util.LoggerUtils;
import com.wsheng.solr.util.QueryUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrActionServiceImpl extends AbstractSolrServer implements ISolrActionService {
	
	protected static Logger logger = Logger.getLogger(SolrActionServiceImpl.class);

	public QueryResponse query(String query) {
		SolrParams params = new SolrQuery(query);
		QueryResponse response = null;
		try {
			response = server.query(params);
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Query failed for the query: " + query + "	" + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}
	
	public QueryResponse query(ModifiableSolrParams params) {

		QueryResponse response = null;
		try {
			response = server.query(params);
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Query docs by  ModifiableSolrParams failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}

	public QueryResponse query(SolrQuery params) {
		QueryResponse response = null;
		try {
			response = server.query(params);
			return response;
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Query docs by SolrQuery failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}

	/**1.
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
String time = "lostTime:["+sdf.format(new Date())+" TO "+sdf.format(new Date())+"]";

2. > 查询

3. 
	 * @param <T>
	 */
	public QueryResponse query(List<QueryField<?>> queryFields, int start, int rows) {
		
		StringBuffer paramsBuilder = new StringBuffer();
		
		// the sort filed should be saved in the order map because of the sort function. can't be HashMap here
		Map<String, ORDER> orderFields	= new LinkedHashMap<String, ORDER>();
		
		List<String> facetFields 	   	= new ArrayList<>();
		List<String> highlightFields 	= new ArrayList<>();
		
		for (QueryField<?> field : queryFields) {
			paramsBuilder = QueryUtils.appendQuery(field, paramsBuilder);
			
			if (field.isSort())
				orderFields.put(field.getName(), field.getSortType());
			if (field.isHighlight())
				highlightFields.add(field.getName());
			if (field.isFacet()) 
				facetFields.add(field.getName());
		}
	
		SolrQuery query = new SolrQuery(paramsBuilder.toString());
		for (Map.Entry<String, ORDER> sortField : orderFields.entrySet()) {
			query.addSort(sortField.getKey(), sortField.getValue());
		}
		// Add Facet Field
		if (facetFields.size() > 0) {
			query.setFacet(true).setFacetMinCount(1).setFacetLimit(100);
			for (String facetFieldName : facetFields) {
				query.addFacetField(facetFieldName);
			}
		}
		
		if (highlightFields.size() > 0) {
			query.setHighlight(true);
			query.setHighlightSimplePre("<font color=\'red\'>"); // html渲染
	        query.setHighlightSimplePost("</font>"); 
	        query.setHighlightFragsize(200); // 设置每个分片的最大长度
	        
	        for (String highLightFieldName : highlightFields) {
	        	query.addHighlightField(highLightFieldName);
	        	// query.setParam("hl.fl", highLightFieldName);
	        }
		}
		
		// 是否分页
		if (start != -1 && rows != -1) {
			query.setStart(start);
			query.setRows(rows);
		}
		
		LoggerUtils.info(logger, " query is: " + CommonsUtils.decode(query.toString()));	
		System.out.println(" query is: " + CommonsUtils.decode(query.toString()));

		try {
			QueryResponse response = server.query(query);
			return response;
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Query docs by QueryFields failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		return null;
		
	}

	public UpdateResponse addDoc(SolrInputDocument doc) {
		UpdateResponse response = null;
		
		try {
			response = server.add(doc);
			server.commit(); // required
			
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Add doc failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Add doc failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		
		return response;
	}

	public UpdateResponse addDocs(Collection<SolrInputDocument> docs) {
		UpdateResponse response = null;
		try {
			response = server.add(docs);
			server.commit();
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Add docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Add docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		
		return response;
	}


	public void removeDoc(String id, boolean needCommit) {
		try {
			server.deleteById(id);
			if (needCommit)
				server.commit();
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Remove doc failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Remove doc failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		
	}
	
	public void removeDocs(List<String> ids, boolean needCommit) {
		try {
			server.deleteById(ids);
			if (needCommit)
				server.commit();
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Remove docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Remove docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		
	}

	
	public void removeDocs(String query, boolean needCommit) {
		try {
			server.deleteByQuery(query);
			if (needCommit)
				server.commit();
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Remove docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Remove docs failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		
	}
	
}
