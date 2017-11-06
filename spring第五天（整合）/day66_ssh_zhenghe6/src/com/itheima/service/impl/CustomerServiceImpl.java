package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void saveCustomer(Customer customer) {
		System.out.println("Service添加客户执行了");
		customerDao.saveCustomer(customer);
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}

}
