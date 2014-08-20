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
	 * ����query������ѯ����
	 * @param query
	 * @return
	 */
	public SolrDocumentList query(String query);
	
	/**
	 * ��ѯ���е���������Ϣ
	 * 
	 * Using *:* 
	 * 
	 * @return
	 */
	public SolrDocumentList queryAll(ModifiableSolrParams params);
	
	/**
	 * ʹ���Զ��������ѯ
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
	 * ���document�ĵ�
	 * @param doc
	 */
	public void addDoc(SolrInputDocument doc);
	
	/**
	 * ����Solr��Ӷ��Document,������ĵ�����
	 * @param docs
	 */
	public void addDocs(Collection<SolrInputDocument> docs);
	
	/**
	 * ��� Entity Bean�����Ͽ�
	 * @param bean
	 */
	public void addBean(SolrBean<String> bean);
	
	/**
	 * ���Entity Bean���ϵ�������
	 * @param beans
	 */
	public void addBeans(Collection<SolrBean<String>> beans);
	
	/**
	 * ɾ������
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
