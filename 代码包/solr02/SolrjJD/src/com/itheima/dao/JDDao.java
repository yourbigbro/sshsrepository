package com.itheima.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.itheima.pojo.ResultModel;

public interface JDDao {

	ResultModel findByQuery(SolrQuery solrQuery) throws SolrServerException;

	
}
