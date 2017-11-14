package com.itheima.service;

import java.util.List;

import com.itheima.domain.Customer;

public interface ICustomerService {

	List<Customer> findAll();

	Customer find(String custId);

	void editCustomer(Customer customer);

}
