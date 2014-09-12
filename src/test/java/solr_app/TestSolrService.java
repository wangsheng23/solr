/**
 * 
 */
package solr_app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wsheng.solr.SolrActionServiceImpl;
import com.wsheng.solr.VegetableActionService;
import com.wsheng.solr.command.SolrImport;
import com.wsheng.solr.model.SolrBean;
import com.wsheng.solr.model.Vegetable;
import com.wsheng.solr.query.QueryField;
import com.wsheng.solr.query.QueryField.FieldType;
import com.wsheng.solr.util.SolrUtils;

/**
 * 仅仅用来测试SolrJ的API，没有任何的Assert
 * 
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
public class TestSolrService extends Assert {
	
	private static SolrActionServiceImpl service;
	
	private static VegetableActionService vService;
	
	@BeforeClass
	public static void init() {
		service = new SolrActionServiceImpl();
		vService = new VegetableActionService();
	}
	
	@Test
	public void queryAll() {
		String query = "*:*";
		
		SolrDocumentList docs = service.query(query).getResults();
		
		System.out.println("doc size: " + docs.size());
		
		for (SolrDocument doc : docs) {
			System.out.println(doc);
		}
	}
	
	@Test
	public void queryById() {
		String query = "id:6";
		SolrDocumentList docs = service.query(query).getResults();
		System.out.println("Number: " + " --- " + docs.getNumFound());
		for (SolrDocument doc : docs) {
			System.out.println(doc);
			
			Collection<String> fieldNames = doc.getFieldNames();
			for (String fieldName : fieldNames) {
				System.out.println(fieldName + " ------" + doc.getFieldValue(fieldName));
			}
			
		}
		
	}
	
	@Test
	public void add() {
		// Get the origin doc if exists.
		SolrDocumentList docs = service.query("id:2").getResults();
		
		for (int i = 0; i < docs.size(); i++) {
			SolrDocument curDoc = docs.get(i);
			
			System.out.println(" Before Added -- " + SolrUtils.getValue(curDoc, "name"));
		}
		
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 2); // 如果存在相同id 的field，则会替换原有的field，相当于update操作。
		doc.addField("name", "Iphone5s");
		doc.addField("manu", "有土豪金哦！！！");
		doc.addField("popularity", 5);
		
		// doc.addField("test", "test");
		
		service.addDoc(doc);
		System.out.println(" Successfully add!!!");
		
		// Get the new created/updated doc .
		docs = service.query("id:2").getResults();
		
		for (int i = 0; i < docs.size(); i++) {
			SolrDocument curDoc = docs.get(i);
			
			System.out.println(" After Added -- " + SolrUtils.getValue(curDoc, "name"));
		}
	}
	
	@Test
	public void remove() {
		service.removeDoc("100", true);
		
		SolrDocumentList docs = service.query("id:100").getResults();
		
		Assert.assertEquals(0, docs.size());
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
		
		doc = new SolrInputDocument();
		doc.addField("id", 8);
		doc.addField("name", "HTC-TopOne");
		doc.addField("manu", "HTC2014年最新力作，音乐功能值得信赖, 用于测试排序。");
		doc.addField("popularity", 9);
		doc.addField("weight", 1.2);
		docs.add(doc);
		
		
		
		service.addDocs(docs);
		
		// SolrJ API does not support this.
//		SolrDocumentList resultDocs = service.query("id: \"6 OR \"");
//		for (SolrDocument sd : resultDocs) {
//			System.out.println(sd);
//		}
		
	}
	
	
	
	@Test
	public void addBeans() {
		Collection<SolrBean<String, Vegetable>> vegetables = new ArrayList<SolrBean<String, Vegetable>>();
		
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
		
		vService.addVegetables(vegetables);
		
	}
	
	@Test
	public void queryByManu() {
		String query = "manu:放心使用,一流体验";
		// String query = "manu:红色";
		SolrDocumentList docs = service.query(query).getResults();
		System.out.println(docs.getNumFound() + " --- ");
		for (int i = 0; i < docs.size(); i++) {
			System.out.println("Query By Manu:" + docs.get(i));
			
			SolrDocument doc = docs.get(i);
			
			System.out.println(" includes value: " + SolrUtils.getValue(doc, "includes"));
		}
		
		System.out.println( " ---- ");
		
		// get the values of the field 
		Set<Object> values = SolrUtils.getValues(docs, "includes");
		
		for (Object value : values) {
			System.out.println(value.toString());
		}
	}
	
	@Test
	public void queryByParams() {
		// SolrQuery params = new SolrQuery("manu:力作 AND name:Nokia");
		//SolrQuery params = new SolrQuery("manu:力作");
		// params.setQuery("name:Nokia");
		
		// params.setQuery("name:HTC OR name:iphone");
		
		SolrQuery params = new SolrQuery("manu:使用 OR (manu:大气  AND manu:土豪金)");
		
		System.out.println("query is: " + params.getQuery());
		SolrDocumentList docs = service.query(params).getResults();
		
		for (SolrDocument doc : docs) {
			System.out.println(doc);
		}
	}
	
	@Test
	public void commonQuery() {
		List<QueryField<?>> fields = new ArrayList<QueryField<?>>();
		
		// The query will be parsed as: Nokia,Iphone5s,HTC. And this equals "Nokia OR Iphone5s OR　HTC"
		List<String> values1 = new ArrayList<String>();
		values1.add("Nokia");
		values1.add("Iphone5s");
		values1.add("HTC");
		// values1.add("Nokia,Iphone5s,HTC");
		// values1.add("Nokia OR Iphone5s OR　HTC");
		
		
		// field1 is the second field - see below:fields.add(field1); facet, highlight, sort by name asc,
		QueryField<String> field1 = new QueryField<String>("name", values1, true, 
				true, true, ORDER.asc, FieldType.Normal, QueryField.FieldConnector.OR); 
		
		// field2 is the third field - see below:fields.add(field2);  facet, no highlight, no sort
		List<String> values2 = new ArrayList<String>();
		values2.add("肉食动物");
		QueryField<String> field2 = new QueryField<String>("manu", values2, true, false, false, null, 
				FieldType.Normal, QueryField.FieldConnector.OR);
		
		
		// field3 is the last field - see below: fields.add(field3);
		// no facet, no highlight, sort by id desc, id is String, so id "2" will ahead of id "100"
		// Note: the field connector of the last field must be null
		QueryField<String> field3 = new QueryField<String>("id", "3", false , true, true, ORDER.desc, 
				FieldType.Normal, null);
		
		List<Integer> values4 = new ArrayList<Integer>();
//		values4.add(3000);
//		values4.add(5000);
		values4.add(1000);
		values4.add(null);
		
		// field4 is the first field - fields.add(field4); no facet, highlight, sort by price desc
		// Range field, sort by price desc firstly
		QueryField<?> field4 = new QueryField<Integer>("price", values4, false, false, true, ORDER.desc,
				FieldType.Normal_Range, QueryField.FieldConnector.OR);
	
	/*	List<Float> values4 = new ArrayList<>();
		values4.add(1.5f);
		values4.add(1.2f);
		// field4 is the first field - fields.add(field4); no facet, highlight, sort by price desc
		// Range field, sort by price desc firstly
		QueryField<?> field4 = new QueryField<Float>("weight", values4, false, false, true, ORDER.desc,
				FieldType.Normal_Range, QueryField.FieldConnector.OR);
	*/
		
		fields.add(field4);
		fields.add(field1);
		fields.add(field2);
		fields.add(field3);
		
		
		QueryResponse response = service.query(fields, -1, -1);
		
		// only show the first 2 docs
		// QueryResponse response = service.query(fields, 1, 2);
		
		SolrDocumentList results = response.getResults();
		
		System.out.println(" total results size: " + results.size());
		
		for (SolrDocument doc : results) {
			System.out.println(doc);
		}
		
		System.out.println("======输出分片信息=======");
		
		// 输出分片信息
		if (SolrUtils.getFacetField(response) != null) {
			for (FacetField facet : SolrUtils.getFacetField(response)) {
				System.out.println(" facet fieldName: " + facet.getName());
				List<Count> facetCounts = facet.getValues();
				for (FacetField.Count count : facetCounts) {
					System.out.println(count.getName() + " : " + count.getCount());
				}
			}
		}
		
		
		System.out.println("======输出高亮的field=======");
		Map<String, Map<String, List<String>>> hResults = SolrUtils.getHighlighFields(response);
		if (hResults != null) {
			for (Map.Entry<String, Map<String, List<String>>> doc : hResults.entrySet()) {
				System.out.println(" doc key(ID): " + doc.getKey() + " ---" + doc.getValue());
				for (Map.Entry<String, List<String>> hFields : doc.getValue().entrySet()) {
					System.out.println(" highlight field key: " + hFields.getKey());
					
					for (String field : hFields.getValue()) {
						System.out.println(" highlight field value: " + field);
					}
				}
				
			} 
		}
		
	}
	
	
	@Test
	public void fullImport() {
		try {
			// full Import will re-index and replace the former ones
			SolrImport.fullImport();
			this.queryById();
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
	

}
