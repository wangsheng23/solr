/**
 * 
 */
package com.wsheng.solr;

import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.wsheng.solr.model.SolrBean;
import com.wsheng.solr.query.QueryField;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public interface ISolrActionService {

	/**
	 * 根据query参数查询索引
	 * @param query
	 * @return
	 */
	public SolrDocumentList query(String query);
	
	/**
	 * 查询所有的索引的信息
	 * 
	 * Using *:* 
	 * 
	 * @return
	 */
	public SolrDocumentList queryAll(ModifiableSolrParams params);
	
	/**
	 * 使用自定义参数查询
	 * 
	 * @return
	 */
	public SolrDocumentList query(SolrQuery params);
	
	
	/**
	 * 
	 * @param queryFields
	 * @param start
	 * @param rows
	 * @return
	 */
	public SolrDocumentList query(List<QueryField> queryFields, int start, int rows);
	
	/**
	 * 添加document文档
	 * @param doc
	 */
	public void addDoc(SolrInputDocument doc);
	
	/**
	 * 利用Solr添加多个Document,即添加文档集合
	 * @param docs
	 */
	public void addDocs(Collection<SolrInputDocument> docs);
	
	/**
	 * 添加 Entity Bean到集合库
	 * @param bean
	 */
	public void addBean(SolrBean<String> bean);
	
	/**
	 * 添加Entity Bean集合到索引库
	 * @param beans
	 */
	public void addBeans(Collection<SolrBean<String>> beans);
	
	/**
	 * 删除索引
	 * @param id
	 */
	public void removeDoc(String id, boolean needCommit);
	
	/**
	 * 
	 * @param ids
	 * @param needCommit
	 */
	public void removeDocs(List<String> ids, boolean needCommit);
	
	public void removeDocs(String query, boolean needCommit);
	
}
