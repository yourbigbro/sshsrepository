package com.itheima.testSolr;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class testSolrj {

	@Test
	//测试solrj的增加和修改
	public void testAddAndEdit() throws Exception {
		
		//连接到solr服务器
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//创建文档
		SolrInputDocument document = new SolrInputDocument();
		//在文档中加入内容
		document.addField("id", "002");
		document.addField("name", "哦哦");
		//执行添加到索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	
	@Test
	//测试solrj的删除
	public void testDelete() throws IOException, SolrServerException {
		
		//连接到solr服务器
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//只能根据id或者query删除，不能通过name等其他属性删除
		//solrServer.deleteById("001");
		solrServer.deleteByQuery("id:001");
		//删除也属于修改，因此也要提交
		solrServer.commit();
	}
	

	@Test
	//测试solrj的查询
	public void testQuery() throws SolrServerException {
		
		//连接到solr服务器
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/");
		//创建查询条件对象
		SolrQuery solrQuery = new SolrQuery();
		//封装查询条件
		solrQuery.setQuery("*:*");
		//执行查询并返回响应
		QueryResponse query = solrServer.query(solrQuery);
		//从响应中得出文档集合
		SolrDocumentList documentList = query.getResults();
		//用两种方法分别打印文档集合的元素数量
		System.out.println("文档集合的元素个数是"+documentList.size());
		System.out.println("文档集合的元素个数是"+documentList.getNumFound());
		//遍历文档集合中的各个文档
		for (SolrDocument solrDocument : documentList) {
			
			//不用强制转换成String，就这样Object呆着就行
			Object id = solrDocument.get("id");
			Object name = solrDocument.get("name");
			System.out.println("id是："+id+",name是："+name);
		}
	}

}
