package com.itheima;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	
	@Test
	public void test1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		//光执行这一句的话只会执行初始化方法和构造函数，不会执行普通方法
		CustomerService cd = (CustomerService)ac.getBean("customerserviceimpl");
		cd.addCustomer();
	}
}
