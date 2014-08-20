/**
 * 
 */
package com.wsheng.solr.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrBean<PK extends Serializable> {

	@Field //setter方法上添加Annotation也是可以的
    public PK id;
    
    
    
    public PK getId() {
        return id;
    }
    
   // @Field
    public void setId(PK id) {
        this.id = id;
    }
    //getter、setter方法
	
	
}
