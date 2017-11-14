package com.itheima.service;

import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.domain.pageBean;

public class ProductService {
	//�����ܵļ�¼��
	public pageBean<Product> setTotal(pageBean<Product> pageBean) {
		ProductDao pd = new ProductDao();
		//����ܵļ�¼��
		int totalRecord=pd.getTotal();
		pageBean.setTotal(totalRecord);
		return pageBean;
	}
	//���õ�ҳ�Ķ��󼯺�
	public pageBean<Product> setRows(pageBean<Product> pageBean) {
		ProductDao pd = new ProductDao();
		//�÷���������������ҳ���ҳ�Ĵ�С
		List<Product> products=pd.getOnePageProducts(pageBean.getPageNumber(),pageBean.getPageSize());
		pageBean.setRows(products);
		return pageBean;
	}

}
