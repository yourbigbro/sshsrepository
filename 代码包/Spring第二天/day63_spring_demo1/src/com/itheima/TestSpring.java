package com.itheima;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	
	@Test
	public void test1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerDao cd = (CustomerDao)ac.getBean("customerdao");
	}
}
