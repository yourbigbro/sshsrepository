package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.dao.inter.IUserDao;
import com.itheima.domain.Customer;
import com.itheima.service.inter.IUserService;


public class UserServiceImpl implements IUserService {

	@Override
	public List<Customer> query() {
		IUserDao user=new UserDaoImpl();
		List<Customer> list=user.query();
		return list;
	}

}
