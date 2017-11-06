package com.itheima.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itheima.dao.JDDao;
import com.itheima.pojo.ProductModel;
import com.itheima.pojo.ResultModel;

@Repository
public class JDDaoImpl implements JDDao {
	
	@Autowired
	private HttpSolrServer solrServer;

	//该方法用于将筛选出来的索引库中的信息显示到商城页面
	@Override
	public ResultModel findByQuery(SolrQuery solrQuery) throws SolrServerException {
		
		ResultModel resultModel = new ResultModel();
		
		ArrayList<ProductModel> ppList = new ArrayList<>();
		
		//创建与索引库的连接
		QueryResponse query = solrServer.query(solrQuery);
		//获取结果集
		SolrDocumentList list = query.getResults();
		//获取结果的数量
		long numFound = list.getNumFound();
		//遍历结果集
		for (SolrDocument doc : list) {
			
			//商品的名字
			String productName;
			
			//从连接query里面获取高亮
			Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
			//获取高亮集合(说是集合其实只是一项)
			List<String> listproductName = highlighting.get(doc.get("id")).get("product_name");
			if(listproductName!=null){
				productName=listproductName.get(0);
			}else {
				//String.valueOf()方法可以将任何类型的数据转换成String类型
				productName=String.valueOf(doc.get("product_name"));
			}
			
			//封装商品数据
			ProductModel model = new ProductModel();
			model.setPid(String.valueOf(doc.get("id")));
			model.setName(productName);
			model.setCatalog_name(String.valueOf("product_catalog_name"));
			model.setPicture(String.valueOf(doc.get("product_picture")));
			if(String.valueOf(doc.get("product_price")) != null 
					&& !"".equals(String.valueOf(doc.get("product_price")))){
				model.setPrice(Float.parseFloat(String.valueOf(doc.get("product_price"))));
			}
			ppList.add(model);
		}
		
		resultModel.setProductList(ppList);
		resultModel.setRecordCount(numFound);
		
		return resultModel;
	}

}
