package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.ICustomerDao;
import com.itheima.dao.impl.CustomerDaoImpl;
import com.itheima.domain.Customer;
import com.itheima.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService {

	@Override
	public List<Customer> findAll() {
		ICustomerDao allProducts=new CustomerDaoImpl();
		List<Customer> list=allProducts.findAll();
		return list;
	}

	@Override
	public Customer find(String custId) {
		ICustomerDao allProducts=new CustomerDaoImpl();
		Customer customer=allProducts.find(custId);
		return customer;
	}
	//用edit.jsp中的信息修改数据库
	@Override
	public void editCustomer(Customer customer) {
		ICustomerDao allProducts=new CustomerDaoImpl();
		allProducts.editCustomer(customer);
	}

}
