package com.itheima.service.impl;

import java.util.List;

import javax.sql.rowset.Predicate;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.dao.JDDao;
import com.itheima.pojo.ProductModel;
import com.itheima.pojo.ResultModel;
import com.itheima.service.JDService;

@Service
public class JDServiceImpl implements JDService {
	
	//注意，不写数值=20的话就会报错
	private static final Integer PAGE_SIZE=20;
	
	@Autowired
	private JDDao jDDao;
	
	//该方法用于返回ResultModel对象
	//注意，实例方法抛出异常的话接口方法必须抛出异常，否则说明他们不是一个方法
	@Override
	public ResultModel findByReturnResultModel(String queryString, String catalog_name, String price, String page,
			String sort) throws SolrServerException {
		
		SolrQuery solrQuery = new SolrQuery();
		
		//设置默认搜索域
		solrQuery.set("df","product_keywords");
		
		//从索引库中搜索信息之前判断用户是否输入了内容
		if(queryString!=null&&!queryString.equals("")){
			//利用默认搜索域来从索引库中搜索用户输入的内容
			solrQuery.setQuery(queryString);
		}else {
			solrQuery.setQuery("*:*");
		}
		
		//根据catalog_name进行过滤(假如没输入的话就不用执行此次过滤了)
		if(catalog_name!=null&&!catalog_name.equals("")){
			solrQuery.setFilterQueries("product_catalog_name:"+catalog_name);
		}
		
		//根据价格进行过滤(没输入的话就不用过滤了)
		if(price!=null&&!price.equals("")){
			String[] split = price.split("-");
			solrQuery.setFilterQueries("product_price:["+split[0]+" TO "+split[1]+"]");
		}
		
		//排序
		if("1".equals(sort)){
			solrQuery.setSort("product_price", ORDER.desc);
		}else {
			solrQuery.setSort("product_price", ORDER.asc);
		}
		
		//开启高亮
		solrQuery.setHighlight(true);
		//设置高亮的域
		//将商品名中的关键字设置为高亮，而并非将整个商品名都设置为高亮(注意是add不是set)
		solrQuery.addHighlightField("product_name");
		//设置高亮关键字的前缀和后缀
		solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</span>");
        
		//设置分页(page属性代表的是当前页码)
        if(page==null||page.equals("")){
        	page="1";
        }
        Integer pageInteger=Integer.parseInt(page);
        //注意，在分页方面Query对象只要这两个数据，而不要总页数等数据，因为有这两个数据就足够了。其他数据是在封装ResultModel的时候使用
        //获得当前页起始索引
		solrQuery.setStart((pageInteger-1)*PAGE_SIZE);
		//获得页的大小
		solrQuery.setRows(PAGE_SIZE);
		
		//进入dao层!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ResultModel resultModel=jDDao.findByQuery(solrQuery);
		
		//ResultModel resultModel = new ResultModel();
		
		//设置当前页
		resultModel.setCurPage(pageInteger);
		
		//计算商品总数量
		Integer count = Integer.parseInt(resultModel.getRecordCount().toString());
		//计算总页数(估计计算当前页和总页数是为了前台标签的显示)
		Integer pageCount=count%PAGE_SIZE==0?count/PAGE_SIZE:count/(PAGE_SIZE+1);
		//设置总页数(注意，parseLong里面只能是String类型)
		resultModel.setPageCount(Long.parseLong(pageCount.toString()));
		resultModel.setRecordCount(Long.parseLong(count.toString()));
		
		return resultModel;
	}

}
