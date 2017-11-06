package com.itheima.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.itheima.pojo.ResultModel;

public interface JDService {

	ResultModel findByReturnResultModel(String queryString, String catalog_name, String price, String page,
			String sort) throws SolrServerException;

	
}
