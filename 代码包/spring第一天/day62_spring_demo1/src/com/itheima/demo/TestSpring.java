package com.itheima.demo;

import org.junit.Test;

//测试spring
public class TestSpring {
	//原来的方法(不用Spring)执行方法
	@Test
	public void test1(){
		UserDao dao=new UserDaoImpl();
		dao.insert();
		
		UserDao dao2=new AdminUserDaoImpl();
		dao2.insert();
	}
	
	//用Spring
	@Test
	public void test2() throws Exception{
		ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("bean.xml");
		UserDao dao = (UserDao)app.getBean("userDao");
		dao.insert();
	}
}
