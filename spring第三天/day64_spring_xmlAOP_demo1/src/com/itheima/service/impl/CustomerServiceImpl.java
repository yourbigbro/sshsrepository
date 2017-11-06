package com.itheima.service.impl;

import com.itheima.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public void addCustomer() {
		System.out.println("添加用户方法执行了");

	}

	@Override
	public void updateCustomer() {
		System.out.println("修改用户方法执行了");

	}

	@Override
	public void deleteCustomer() {
		System.out.println("删除用户方法执行了");
		int num=10/0;
		System.out.println("删除用户方法执行完毕");

	}

}
