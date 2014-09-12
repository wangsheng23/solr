/**
 * 
 */
package com.wsheng.solr;

import java.util.Collection;
import java.util.List;



import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.wsheng.solr.query.QueryField;

/**
 * document �� solr ��������Ϣ��Ԫ�����solr����������һ�����ݿ��Ļ���document ����һ����¼��
 * field ����ֶΣ�һ�� document �ɶ�� field ���ɡ�field ���Ա�������������ͣ��������ݿ��ֶο����и������ͣ�
 * varchar��number�ȣ�һ�� field Ҳ���ı����͡��ַ������͡������ͣ���Щ���ͳ�֮Ϊ field type��
 * ��Щ field type �͸��� solr ��ôȥ�����Ͳ�ѯ����ֶε����ݡ�
 * 
 * indexed = true means want to search on it
 * stored = true means want it to be retrievable on the search result
 * 
 * 
	indexed���������ָ���ֶ��Ƿ��ܲ�����һ�����˵���˾����Ƿ�Ҫ���зִʷ�����ͨ�������������true��
	false������ǳ��ټ���ͨ�����ĳ�ֶ�ֻ������ʾ�Ļ��������ó�indexed="false"
	stored���������ָ��Ӧ���ֶε�����������������Ƿ����ʾ��Ҳ�����Ƿ���ԭ����Ϣ��
	���������ҪSolr�洢�������ݣ���Hello, Dian Di����Solr�ִʴ���֮�󣬱������ʵ��һ��һ���ؼ��֣�
	���Ǿ��ӱ����ˣ���ˣ����stored="false"�������޷���ԭ�ĵ����ݷ��ظ��û��ģ���Ϊԭ���Ѿ�û���ˡ�
	����ֻ�н�stored="true"�����������û�������Dian Di��ʱ�����ܽ�ԭ�ķ��ظ��û��������û��鿴��
 * 
 * if the query not specifying the handler, standard request handler will be applied.
 * Before Sorl 1.3, Standard request handler called the standard query parser as the default query parser.
 * Since Solr 1.3, the standard request handler calls the DisMax query parser(Maximum Disjunction) as the default query parser
 * Whether or not you remember this explanation, do remember that the DisMax request handler was primarily designed to be easy to use and to
   accept almost any input without returning an error.
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public interface ISolrActionService {

	/**
	 * �ɸ�����query������ѯ�ı�
	 * @param query
	 * @return
	 */
	public QueryResponse query(String query);
	
	/**
	 * ��ѯ���������������ı���Ϣ
	 * 
	 * @param params	using *:* to query all of the documents
	 * @return
	 */
	public QueryResponse query(ModifiableSolrParams params);
	
	/**
	 * ��������SolrQuery�Ĳ�ѯ�ı�
	 * 
	 * @param params	SolrQuery
	 * @return
	 */
	public QueryResponse query(SolrQuery params);
	
	
	/**
	 * �Զ���߼���ѯField, ��ָ��Field�Ƿ���������Ƿ������ʾ��
	 * 
	 * @param queryFields
	 * @param start
	 * @param rows
	 * @return
	 */
	public QueryResponse query(List<QueryField<?>> queryFields, int start, int rows);
	
	/**
	 * ���document�ĵ�
	 * @param doc
	 */
	public UpdateResponse addDoc(SolrInputDocument doc);
	
	/**
	 * ������Ӷ��Document
	 * @param docs	��ӵ��ĵ���
	 */
	public UpdateResponse addDocs(Collection<SolrInputDocument> docs);
	
	/**
	 * �����ĵ�idɾ���ĵ�
	 * @param id
	 */
	public void removeDoc(String id, boolean needCommit);
	
	/**
	 * ȷ���Ƿ�ɾ��idsָ�����ĵ���
	 * @param ids	
	 * @param needCommit
	 */
	public void removeDocs(List<String> ids, boolean needCommit);
	
	/**
	 * ȷ���Ƿ�ɾ��query��ѯ�����ĵ�/�ĵ���
	 * 
	 * @param query
	 * @param needCommit
	 */
	public void removeDocs(String query, boolean needCommit);
	
}
