/**
 * 
 */
package com.wsheng.solr.util;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


/**
 * @author Josh Wang(Sheng)
 * @email  joshwang23@hotmail.com
 */
public class HttpUtils {
    
    private static Log logger = LogFactory.getLog(HttpUtils.class);
    
    public static String handleSolrReq(String url) throws Exception {
            
        Client client = Client.create();
 
        WebResource webResource = client.resource(url);
 
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
 
        if (response.getStatus() != 200) {
        	logger.error("Failed...." + response.getStatus());
        	System.out.println("Failed...." + response.getStatus() + "  " + response.getEntity(String.class));
           throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
 
        String result = response.getEntity(String.class);
              
        return result;
    }
    
    
}
