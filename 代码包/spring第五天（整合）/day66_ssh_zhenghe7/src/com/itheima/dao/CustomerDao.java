package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Customer;

public interface CustomerDao {
	public void saveCustomer(Customer customer);

	public List<Customer> findAllCustomer();
}
