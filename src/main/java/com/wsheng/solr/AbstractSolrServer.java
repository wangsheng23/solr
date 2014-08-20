/**
 * 
 */
package com.wsheng.solr;


import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class AbstractSolrServer {
	
	/**TODO: Using Spring injection*/
	public static SolrServer server;
	
	static {
			server = new HttpSolrServer("http://localhost:9898/solr/item");
	}
}
