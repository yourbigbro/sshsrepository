package com.itheima.dao.impl;

import java.util.List;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void saveCustomer(Customer customer) {
		System.out.println("Dao添加客户执行了");
	}

	@Override
	public List<Customer> findAllCustomer() {
		System.out.println("Dao查找客户执行了");
		return null;
	}

}
