package com.itheima;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("customerserviceimpl")
public class CustomerServiceImpl implements CustomerService {
	
	//该变量需要赋值，此处我们根据注解赋值
	//@Autowired
	//@Qualifier("customerdaoimpl")
	@Resource(name="linkmandaoimpl")
	private CustomerDao customerdaoimpl11;//注意用的是接口CustomerDao而不是实现接口的具体类
	@Override
	public void addCustomer() {
		customerdaoimpl11.addCustomer();

	}

}
