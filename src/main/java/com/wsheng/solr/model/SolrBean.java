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

	@Field //setter���������AnnotationҲ�ǿ��Ե�
    public PK id;
    
    
    
    public PK getId() {
        return id;
    }
    
   // @Field
    public void setId(PK id) {
        this.id = id;
    }
    //getter��setter����
	
	
}
