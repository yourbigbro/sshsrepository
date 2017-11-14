package com.itheima.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

//注意，只能是extends在前面implements在后面
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	//根据custId从数据库中查询出相应的Customer对象
	@Override
	public Customer findCustById(Long custId) {
		//这个不用强制转型，因为Customer.class已经表明了应该返回的对象类型
		return getHibernateTemplate().get(Customer.class,custId);
	}
	
	
}
