package com.itheima.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itheima.pojo.ResultModel;
import com.itheima.service.JDService;

@Controller
public class JDController {
	
	@Autowired
	private JDService jDService;

	@RequestMapping("list")
	//queryString用户输入的商品名称，catalog_name商品类别，price商品价格区间，page当前页码，sort确定是升序排列还是降序排列
	public String findGoods(Model model,String queryString,String catalog_name,String price,String page,String sort) throws SolrServerException{
		
		ResultModel resultModel=jDService.findByReturnResultModel(queryString,catalog_name,price,page,sort);
		
		//将信息封装进model对象中
		model.addAttribute("result", resultModel);
		model.addAttribute("queryString",queryString);
		model.addAttribute("catalog_name",catalog_name);
		model.addAttribute("price",price);
		model.addAttribute("sort",sort);
		return "product_list";
	}
}
