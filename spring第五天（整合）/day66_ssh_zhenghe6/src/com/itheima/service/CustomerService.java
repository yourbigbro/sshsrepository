package com.itheima.service;

import java.util.List;

import com.itheima.domain.Customer;

public interface CustomerService {
	public void saveCustomer(Customer customer);

	public List<Customer> findAllCustomer();
}
