/**
 * 
 */
package solr_app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wsheng.solr.SolrActionServiceImpl;
import com.wsheng.solr.command.SolrImport;
import com.wsheng.solr.model.SolrBean;
import com.wsheng.solr.model.Vegetable;
import com.wsheng.solr.query.QueryField;

/**
 * Solr Facet search can reference 3 document:
 * 1) http://martin3000.iteye.com/blog/1330106
 * 
 * 2) http://macrochen.iteye.com/blog/1337576
 * 
 * 3) http://www.cnblogs.com/hoojo/archive/2011/10/21/2220431.html
 * 
 * Solr可以利用DocumentObjectBinder对象将SolrInputDocument 和 User对象相互转换
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class TestSolrServer extends Assert {
	
	private static SolrActionServiceImpl service;
	
	@BeforeClass
	public static void init() {
		service = new SolrActionServiceImpl();
	}
	
	// indexed = true means want to search on it
	// stored = true means want it to be retrievable on the search result
	
	/*
	 * indexed这个属性是指该字段是否能参与查找或排序，说白了就是是否要进行分词分析，通常情况下它都是true，
	 * false的情况非常少见，通常如果某字段只用来显示的话可以设置成indexed="false"
	
	    stored这个属性是指对应的字段的内容在搜索结果中是否可显示，也就是是否保留原文信息。是不是觉得有点不明白？
		举例来说，我们需要Solr存储以下内容：“Marry is a little lamb”，Solr分词处理之后，保存的其实是一个一个关键字，
		而非句子本身了，因此，如果stored="false"，你是无法将原文的内容返回给用户的，因为原文已经没有了。
		所以只有将stored="true"，这样，当用户搜索“lamb”时，就能将原文返回给用户，便于用户查看。
	 */
	
	@Test
	public void fullImport() {
		try {
			// full Import will re-index and replace the former ones
			SolrImport.fullImport();
			this.query();
			System.out.println("=============");
			this.queryByManu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deltaImport() {
		try {
			SolrImport.deltaImport();
			
			System.out.println("Delta import completed");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void query() {
		String query = "id:6";
		SolrDocumentList list = service.query(query);
		System.out.println(list.getNumFound() + " --- ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void queryByManu() {
		// String query = "manu:放心使用,一流体验";
		String query = "manu:红色";
		SolrDocumentList list = service.query(query);
		System.out.println(list.getNumFound() + " --- ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Query By Manu:" + list.get(i));
		}
	}
	
	@Test
	public void add() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 2); // 如果存在相同id 的field，则会替换原有的field，相当于update操作。
		doc.addField("name", "Iphone5s,5c");
		doc.addField("manu", "有土豪金哦！！！");
		doc.addField("popularity", 5);
		
		// doc.addField("test", "test");
		
		service.addDoc(doc);
		System.out.println(" Successfully add!!!");
		
		this.query();
	}
	
	@Test
	public void remove() {
		service.removeDoc("2", true);
		
		this.query();
		
		this.queryByManu();
	}
	
	@Test
	public void addDocs() {
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 6);
		doc.addField("name", "HTC-TopOne");
		doc.addField("manu", "HTC2014年最新力作，音乐功能值得信赖。");
		doc.addField("popularity", 9);
		doc.addField("weight", 1.2);
		docs.add(doc);
		
		doc = new SolrInputDocument();
		doc.addField("id", 7);
		doc.addField("name", "Nokia Lumia 2000");
		doc.addField("manu", "微软收购诺基亚以来的最新力作，各项功能都为市场之最。");
		doc.addField("popularity", 10);
		doc.addField("weight", 0.8);
		docs.add(doc);
		
		service.addDocs(docs);
	}
	
	@Test
	public void queryByParams() {
		// SolrQuery params = new SolrQuery("manu:力作 AND name:Nokia");
		SolrQuery params = new SolrQuery("manu:力作");
		params.setQuery("name:Nokia");
		
		//params.setQuery("name:HTC OR name:iphone");
		
		System.out.println("query is: " + params.getQuery());
		SolrDocumentList list = service.query(params);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void commonQuery() {
		List<QueryField> fields = new ArrayList<QueryField>();
		
		List<String> values1 = new ArrayList<String>();
		values1.add("Nokia");
		values1.add("Iphone5s");
		QueryField field1 = new QueryField("name", values1, QueryField.FiledConnector.OR); 
		
		List<String> values2 = new ArrayList<String>();
		values2.add("力作");
		QueryField field2 = new QueryField("manu", values2, null);
		
		QueryField field3 = new QueryField("id", null, null); 
		
//		QueryField field2 = new QueryField("manu", values2, QueryField.FiledConnector.AND); 
//		List<String> values3 = new ArrayList<String>();
//		values3.add("6");
//		QueryField field3 = new QueryField("id", values3, null);
		
		field3.setSort(true);
		field3.setSortType(ORDER.asc);
		
		fields.add(field1);
		fields.add(field2);
		fields.add(field3);
		
		SolrDocumentList list = service.query(fields, -1, -1);
		 // SolrDocumentList list = service.query(fields, 1, 2);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void addBeans() {
		Collection<SolrBean<String>> vegetables = new ArrayList<SolrBean<String>>();
		
		Vegetable v1 = new Vegetable();
		v1.setId("100");
		v1.setName("新鲜的大白菜");
		v1.setManu("素食首选");
		v1.setCat(new String[] {"有机", "无机"});
		
		Vegetable v2 = new Vegetable();
		v2.setId("101");
		v1.setName("新疆牛肉");
		v1.setManu("肉食动物");
		v1.setCat(new String[] {"午餐肉", "烤肉"});
		
		vegetables.add(v1);
		vegetables.add(v2);
		
		service.addBeans(vegetables);
		
	}
	
	@Test
	public void queryAll() {
		String query = "*:*";
		
		SolrDocumentList list = service.query(query);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
