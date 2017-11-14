package com.itheima.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void saveCustomer(Customer customer) {
		//getHibernateTemplate()只是使用了连接，并不代表使用了事务。事务是在配置文件里面配置的
		getHibernateTemplate().save(customer);
	}

	@Override
	public List<Customer> findAllCustomer() {
		//注意find方法是要对结果进行强制转型的
		return (List<Customer>) getHibernateTemplate().find("from Customer");
	}

}
