package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.Customer;
import com.itheima.dao.CustomerDao;
import com.itheima.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	//该方法用于测试找到所有信息
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	//找出没有定区id的客户
	@Override
	public List<Customer> findByFixedAreaIdIsNull() {
		return customerDao.findByFixedAreaIdIsNull();
	}

	//找出定区fixedAreaId为某值的客户
	@Override
	public List<Customer> findByFixedAreaId(String fixedAreaId) {
		return customerDao.findByFixedAreaId(fixedAreaId);
	}

	//将右边选项框内的集合中的customer对象的fixedAreaId属性都变为null
	@Override
	public void setFixedAreaIdNull(List<Customer> list) {
		//循环修改数据库
		for (Customer customer : list) {
			String fixedAreaId = customer.getFixedAreaId();
			customerDao.setFixedAreaIdNull(fixedAreaId);
		}
		
	}

	//该方法用于改变指定id的customer对象的fixedAreaId属性
	@Override
	public void changeFixedAreaIdById(String theFixedAreaId,  Integer id) {
		customerDao.changeFixedAreaIdById(theFixedAreaId,id);
	}

	//保存新用户
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
		
	}

	//为指定电话的用户设置邮箱绑定
	@Override
	public void setEmail(String email, String telephone) {
		customerDao.setEmailByTelephone(email,telephone);
		
	}

	//根据地址查找customer对象
	@Override
	public List<Customer> findByAddress(String address) {
		return customerDao.findByAddress( address);
	}

	//判断用户是否登录成功
	@Override
	public Customer findByTelephoneAndPassword(String telephone, String password) {
		return customerDao.findByTelephoneAndPassword(telephone, password);
	}


	
}
