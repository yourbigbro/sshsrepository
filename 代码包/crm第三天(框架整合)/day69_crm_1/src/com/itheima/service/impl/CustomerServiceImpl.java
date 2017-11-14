package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

@Service("customerService")
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<BaseDict> findCustSource() {
		return customerDao.findCustSource("002");
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<BaseDict> findCustLevel() {
		return customerDao.findCustSource("006");
	}
	
	//保存新用户
	@Override
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);
	}
	
	//查询出所有的Customer(用户)
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public List<Customer> listAllCustomer() {
		return customerDao.listAllCustomer();
	}
	
	//根据id查询Customer对象
	@Override
	public Customer queryByCustId(Integer custId) {
		return customerDao.queryByCustId(custId);
	}
	
	//修改用户信息
	@Override
	public void reSaveCustomer(Customer customer) {
		customerDao.reSaveCustomer(customer);
		
	}
	
	//删除一条用户信息
	@Override
	public void removeCustomer(Customer customer) {
		customerDao.removeCustomer(customer);
		
	}
	
	//筛选信息
	@Override
	public List<Customer> findCustomersByCriteria(DetachedCriteria criteria) {
		return customerDao.findCustomersByCriteria(criteria); 
	}

}
