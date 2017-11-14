package com.itheima.service.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	
	//注意，CustomerDao需要声明为私有成员变量并且用set/get方法，这样才能从配置文件中通过反射取到
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	//根据custId在数据库内查询出Customer相应的对象
	@Override
	public Customer findCustById(Long custId) {
		return customerDao.findCustById(custId);
	}

}
