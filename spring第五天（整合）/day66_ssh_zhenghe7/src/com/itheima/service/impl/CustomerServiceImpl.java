package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

@Service("customerService")
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
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
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Customer> findAllCustomer() {
		return customerDao.findAllCustomer();
	}

}
