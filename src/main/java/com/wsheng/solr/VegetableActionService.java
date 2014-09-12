/**
 * 
 */
package com.wsheng.solr;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;

import com.wsheng.solr.model.SolrBean;
import com.wsheng.solr.model.Vegetable;
import com.wsheng.solr.util.ExceptionUtils;
import com.wsheng.solr.util.LoggerUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class VegetableActionService extends SolrActionServiceImpl {
	public UpdateResponse addVegetable(SolrBean<String, Vegetable> bean) {
		UpdateResponse response = null;
		try {
			response = server.addBean(bean);
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Add doc(Bean) failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Add doc(Bean) failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		return response;
	}

	public UpdateResponse addVegetables(Collection<SolrBean<String, Vegetable>> beans) {
		UpdateResponse response = null;
		try {
			response = server.addBeans(beans);
			server.commit(); 
		} catch (SolrServerException e) {
			LoggerUtils.error(logger, "Add docs(Bean) failed "  + ExceptionUtils.getStackTraceMsg(e));
		} catch (IOException e) {
			LoggerUtils.error(logger, "Add docs(Bean) failed "  + ExceptionUtils.getStackTraceMsg(e));
		}
		
		return response;
	}
}
