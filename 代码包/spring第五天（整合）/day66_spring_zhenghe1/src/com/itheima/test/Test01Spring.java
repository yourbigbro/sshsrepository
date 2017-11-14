package com.itheima.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

//该类(该项目)的作用是单独测试spring框架是否好用，以服务于整合spring和hibernate

//该类也并未配置连接池，因为只是用于测试，并未连接数据库，那是hibernate的事
public class Test01Spring {
	
	@Test
	public void test1(){
		ApplicationContext ac= new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService cs = (CustomerService) ac.getBean("customerService");
		//Customer customer = new Customer();
		cs.findAllCustomer();
	}
}
