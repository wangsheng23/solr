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
 * document 是 solr 基本的信息单元，如果solr的索引库是一张数据库表的话，document 就是一条记录，
 * field 表的字段，一个 document 由多个 field 构成。field 可以保存多种数据类型，就想数据库字段可以有各种类型：
 * varchar、number等，一个 field 也有文本类型、字符串类型、浮点型，这些类型称之为 field type，
 * 这些 field type 就告诉 solr 怎么去索引和查询这个字段的数据。
 * 
 * indexed = true means want to search on it
 * stored = true means want it to be retrievable on the search result
 * 
 * 
	indexed这个属性是指该字段是否能参与查找或排序，说白了就是是否要进行分词分析，通常情况下它都是true，
	false的情况非常少见，通常如果某字段只用来显示的话可以设置成indexed="false"
	stored这个属性是指对应的字段的内容在搜索结果中是否可显示，也就是是否保留原文信息。
	例如如果需要Solr存储以下内容：“Hello, Dian Di”，Solr分词处理之后，保存的其实是一个一个关键字，
	而非句子本身了，因此，如果stored="false"，你是无法将原文的内容返回给用户的，因为原文已经没有了。
	所以只有将stored="true"，这样，当用户搜索“Dian Di”时，就能将原文返回给用户，便于用户查看。
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
	 * 由给定的query参数查询文本
	 * @param query
	 * @return
	 */
	public QueryResponse query(String query);
	
	/**
	 * 查询所有满足条件的文本信息
	 * 
	 * @param params	using *:* to query all of the documents
	 * @return
	 */
	public QueryResponse query(ModifiableSolrParams params);
	
	/**
	 * 构建基于SolrQuery的查询文本
	 * 
	 * @param params	SolrQuery
	 * @return
	 */
	public QueryResponse query(SolrQuery params);
	
	
	/**
	 * 自定义高级查询Field, 可指定Field是否进行排序，是否高亮显示。
	 * 
	 * @param queryFields
	 * @param start
	 * @param rows
	 * @return
	 */
	public QueryResponse query(List<QueryField<?>> queryFields, int start, int rows);
	
	/**
	 * 添加document文档
	 * @param doc
	 */
	public UpdateResponse addDoc(SolrInputDocument doc);
	
	/**
	 * 批量添加多个Document
	 * @param docs	添加的文档集
	 */
	public UpdateResponse addDocs(Collection<SolrInputDocument> docs);
	
	/**
	 * 基于文档id删除文档
	 * @param id
	 */
	public void removeDoc(String id, boolean needCommit);
	
	/**
	 * 确认是否删除ids指定的文档集
	 * @param ids	
	 * @param needCommit
	 */
	public void removeDocs(List<String> ids, boolean needCommit);
	
	/**
	 * 确认是否删除query查询出的文档/文档集
	 * 
	 * @param query
	 * @param needCommit
	 */
	public void removeDocs(String query, boolean needCommit);
	
}
