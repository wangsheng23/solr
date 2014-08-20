/**
 * 
 */
package com.wsheng.solr.command;

import com.wsheng.solr.AbstractSolrServer;
import com.wsheng.solr.util.HttpUtils;

/**
 * The API also should be moved to ISolrActionService
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrImport extends AbstractSolrServer {

	private static String CONFIGURATION_URL = "http://localhost:9898/solr/item/dataimport";
	
	private static String FULL_IMPORT_URL_ITEM = "http://localhost:9898/solr/item/item_dataimport?command=full-import";
	
	private static String DELTA_IMPORT_URL_ITEM = "http://localhost:9898/solr/item/item_dataimport?command=delta-import";
	
	private static String FULL_IMPORT_URL_COLLECTION1 = "http://localhost:9898/solr/collection1/collection1_dataimport?command=full-import";
	
	private static String DELTA_IMPORT_URL_COLLECTION1 = "http://localhost:9898/solr/collection1/collection1_dataimport?command=delta-import";

	
	// If the configuration file has been changed and you wish to reload it without restarting Solr
	// private static String RELOAD_CONFIG = "http://localhost:9898/solr/item/dataimport?command=reload-config";
	
	// It returns statistics on the number of documents created, deleted, queries run, rows fetched, status, and so on.
	// private static String STATUS = "http://localhost:9898/solr/item/dataimport?command=status";
	
	// Aborts an ongoing operation
	// private static String abort = "http://localhost:9898/solr/item/dataimport?command=abort";
	
	public static String verify() throws Exception {
		return HttpUtils.handleSolrReq(CONFIGURATION_URL);
	}
	
	/**
	 * Full Import for this configuration in data-config.xml
	 * 
	 * FUll Import将会删掉之前的索引并全部重新做索引
	 * 
	 * <dataConfig>
		<dataSource name="solrDB" type="JdbcDataSource" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/solr" user="root" password="tools2013"/>
		<document>
		    <entity dataSource="solrDB" name="solr_item"  query="select * from solr_item">
		        <field column="ID" name="id"/>
		        <field column="NAME" name="name"/>
		        <field column="MANU" name="manu"/>
		        <field column="PRICE" name="price"/>
		        <field column="POPULRITY" name="popularity"/>
		        <field column="INCLUDES" name="includes"/>
		    </entity>
		</document>
		</dataConfig>
	 * @throws Exception 
	 * 
	 */
	public static void fullImport() throws Exception {
	
		String result1 = HttpUtils.handleSolrReq(FULL_IMPORT_URL_ITEM);
		
		System.out.println("Import Ended " + result1);
		
//		String result2 = HttpUtils.handleSolrReq(FULL_IMPORT_URL_COLLECTION1);
//		
//		System.out.println("Import Ended " + result2);
	}
	
	public static void deltaImport() throws Exception {
		String result1 = HttpUtils.handleSolrReq(DELTA_IMPORT_URL_ITEM);
		
		System.out.println("Import Ended " + result1);
		
//		String result2 = HttpUtils.handleSolrReq(DELTA_IMPORT_URL_COLLECTION1);
//		
//		System.out.println("Import Ended " + result2);
	}
	
}
