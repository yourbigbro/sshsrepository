package com.itheima.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

@Repository("customerDao")
//用注解之后就不能再实现HibernateDaoSupport类
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveCustomer(Customer customer) {
		hibernateTemplate.save(customer);
		
	}

	@Override
	public List<Customer> findAllCustomer() {
		
		return (List<Customer>) hibernateTemplate.find("from Customer");
	}

}
