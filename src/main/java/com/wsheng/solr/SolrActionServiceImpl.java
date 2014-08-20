/**
 * 
 */
package com.wsheng.solr;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import com.wsheng.solr.model.SolrBean;
import com.wsheng.solr.query.QueryField;
import com.wsheng.solr.util.QueryUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrActionServiceImpl extends AbstractSolrServer implements ISolrActionService {
	

	public SolrDocumentList query(String query) {
		SolrParams params = new SolrQuery(query);
		SolrDocumentList list = null;
		try {
			QueryResponse response = server.query(params);
			list = response.getResults();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addDoc(SolrInputDocument doc) {
		try {
			UpdateResponse response = server.add(doc);
			System.out.println(response.getStatus() + "  ---  " + response.getQTime());
			
			server.commit(); // required
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void addDocs(Collection<SolrInputDocument> docs) {
		try {
			UpdateResponse response = server.add(docs);
			System.out.println(response.getStatus() + "  ---  " + response.getQTime());
			
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addBean(SolrBean<String> bean) {
		try {
			UpdateResponse response = server.addBean(bean);
			System.out.println(response.getStatus() + "  ---  " + response.getQTime());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
	}

	public void addBeans(Collection<SolrBean<String>> beans) {
		try {
			UpdateResponse response = server.addBeans(beans);
			System.out.println(response.getStatus() + "  ---  " + response.getQTime());
			server.commit(); // commit 后才保存到索引库
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void removeDoc(String id, boolean needCommit) {
		try {
			server.deleteById(id);
			if (needCommit)
				server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeDocs(List<String> ids, boolean needCommit) {
		try {
			server.deleteById(ids);
			if (needCommit)
				server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void removeDocs(String query, boolean needCommit) {
		try {
			server.deleteByQuery(query);
			if (needCommit)
				server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public SolrDocumentList queryAll(ModifiableSolrParams params) {

		SolrDocumentList docs = null;
		try {
			QueryResponse response = server.query(params);
			docs = response.getResults();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docs;
	}

	public SolrDocumentList query(SolrQuery params) {
		try {
			QueryResponse response = server.query(params);
			SolrDocumentList list = response.getResults();
			return list;
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public SolrDocumentList query(List<QueryField> queryFields, int start, int rows) {
		
StringBuilder paramsBuilder = new StringBuilder();
		
		Map<String, ORDER> orderFields = new HashMap<String, ORDER>();
		
		for (QueryField field : queryFields) {
			paramsBuilder = QueryUtils.appendQuery(field, paramsBuilder);
			
			if (field.isSort())
				orderFields.put(field.getName(), field.getSortType());
		}
System.out.println(" param is : " + paramsBuilder.toString());	
		SolrQuery query = new SolrQuery(paramsBuilder.toString());
		for (Map.Entry<String, ORDER> sortField : orderFields.entrySet()) {
			query.addSort(sortField.getKey(), sortField.getValue());
		}
		
		query.setFacet(true).setFacetMinCount(1).setFacetLimit(100)
		.addFacetField("name");
		
System.out.println(" query is: " + query);		

		//是否分页
		if (start != -1 && rows != -1) {
			query.setStart(start);
			query.setRows(rows);
		}
	

		try {
			QueryResponse response = server.query(query);
			SolrDocumentList list = response.getResults();
			
			// 输出分片信息
//			List<FacetField> facets = response.getFacetFields();
//			for (FacetField facet : facets) {
//				List<Count> facetCounts = facet.getValues();
//				for (FacetField.Count count : facetCounts) {
//					System.out.println(count.getName() + " : " + count.getCount());
//				}
//			}
//			
			
			return list;
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

	
}
