package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
	
	//通过注解根据配置文件得到hibernateTemplate对象
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public List<BaseDict> findCustSource(String string) {
		return (List<BaseDict>)hibernateTemplate.find("from BaseDict where dictTypeCode=?", string);
	}
	
	//保存用户
	@Override
	public void saveCustomer(Customer customer) {
		hibernateTemplate.save(customer);
		
	}
	
	//找到所有用户(Customer集合)
	@Override
	public List<Customer> listAllCustomer() {
		//注意强制转换
		List<Customer> customers=(List<Customer>) hibernateTemplate.find("from Customer");
		return customers;
	}
	
	//根据id查询Customer对象(也就是某一个用户的信息)
	@Override
	public Customer queryByCustId(Integer custId) {
		return ((List<Customer>)hibernateTemplate.find("from Customer where custId=?",custId)).get(0);
	}
	
	//修改用户信息
	@Override
	public void reSaveCustomer(Customer customer) {
		hibernateTemplate.update(customer);
	}
	
	//删除一条用户信息
	@Override
	public void removeCustomer(Customer customer) {
		hibernateTemplate.delete(customer);
		
	}
	
	//筛选用户信息
	@Override
	public List<Customer> findCustomersByCriteria(DetachedCriteria criteria) {
		
		//注意强制转型
		return (List<Customer>) hibernateTemplate.findByCriteria(criteria);
	}

	//查询当页的所有记录对象的集合
	@Override
	//注意，类型是DetachedCriteria而不是Criteria
	public List<Customer> queryCustomerList(DetachedCriteria criteria, int startIndex, int pageSize) {
		return (List<Customer>)hibernateTemplate.findByCriteria(criteria, startIndex, pageSize);
	}

	//根据改造后的criteria对象查询记录总数
	@Override
	public List<Long> queryCount(DetachedCriteria criteria) {
		return (List<Long>) hibernateTemplate.findByCriteria(criteria);
	}

	/*@Override
	public List<BaseDict> findCustLevel(String string) {
		return (List<BaseDict>)hibernateTemplate.find("from BaseDict where dictTypeCode=?", string);
	}*/
}
