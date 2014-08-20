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
 * Solr��������DocumentObjectBinder����SolrInputDocument �� User�����໥ת��
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
	 * indexed���������ָ���ֶ��Ƿ��ܲ�����һ�����˵���˾����Ƿ�Ҫ���зִʷ�����ͨ�������������true��
	 * false������ǳ��ټ���ͨ�����ĳ�ֶ�ֻ������ʾ�Ļ��������ó�indexed="false"
	
	    stored���������ָ��Ӧ���ֶε�����������������Ƿ����ʾ��Ҳ�����Ƿ���ԭ����Ϣ���ǲ��Ǿ����е㲻���ף�
		������˵��������ҪSolr�洢�������ݣ���Marry is a little lamb����Solr�ִʴ���֮�󣬱������ʵ��һ��һ���ؼ��֣�
		���Ǿ��ӱ����ˣ���ˣ����stored="false"�������޷���ԭ�ĵ����ݷ��ظ��û��ģ���Ϊԭ���Ѿ�û���ˡ�
		����ֻ�н�stored="true"�����������û�������lamb��ʱ�����ܽ�ԭ�ķ��ظ��û��������û��鿴��
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
		// String query = "manu:����ʹ��,һ������";
		String query = "manu:��ɫ";
		SolrDocumentList list = service.query(query);
		System.out.println(list.getNumFound() + " --- ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Query By Manu:" + list.get(i));
		}
	}
	
	@Test
	public void add() {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 2); // ���������ͬid ��field������滻ԭ�е�field���൱��update������
		doc.addField("name", "Iphone5s,5c");
		doc.addField("manu", "��������Ŷ������");
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
		doc.addField("manu", "HTC2014���������������ֹ���ֵ��������");
		doc.addField("popularity", 9);
		doc.addField("weight", 1.2);
		docs.add(doc);
		
		doc = new SolrInputDocument();
		doc.addField("id", 7);
		doc.addField("name", "Nokia Lumia 2000");
		doc.addField("manu", "΢���չ�ŵ������������������������ܶ�Ϊ�г�֮�");
		doc.addField("popularity", 10);
		doc.addField("weight", 0.8);
		docs.add(doc);
		
		service.addDocs(docs);
	}
	
	@Test
	public void queryByParams() {
		// SolrQuery params = new SolrQuery("manu:���� AND name:Nokia");
		SolrQuery params = new SolrQuery("manu:����");
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
		values2.add("����");
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
		v1.setName("���ʵĴ�ײ�");
		v1.setManu("��ʳ��ѡ");
		v1.setCat(new String[] {"�л�", "�޻�"});
		
		Vegetable v2 = new Vegetable();
		v2.setId("101");
		v1.setName("�½�ţ��");
		v1.setManu("��ʳ����");
		v1.setCat(new String[] {"�����", "����"});
		
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
