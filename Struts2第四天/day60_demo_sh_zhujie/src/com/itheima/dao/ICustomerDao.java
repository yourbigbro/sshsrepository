package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Customer;

public interface ICustomerDao {

	List<Customer> findAll();

	Customer find(String custId);

	void editCustomer(Customer customer);

}
