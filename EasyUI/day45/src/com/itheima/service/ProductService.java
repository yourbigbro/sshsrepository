package com.itheima.service;

import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.domain.pageBean;

public class ProductService {
	//设置总的记录数
	public pageBean<Product> setTotal(pageBean<Product> pageBean) {
		ProductDao pd = new ProductDao();
		//获得总的记录数
		int totalRecord=pd.getTotal();
		pageBean.setTotal(totalRecord);
		return pageBean;
	}
	//设置当页的对象集合
	public pageBean<Product> setRows(pageBean<Product> pageBean) {
		ProductDao pd = new ProductDao();
		//该方法的两个参数是页码和页的大小
		List<Product> products=pd.getOnePageProducts(pageBean.getPageNumber(),pageBean.getPageSize());
		pageBean.setRows(products);
		return pageBean;
	}

}
