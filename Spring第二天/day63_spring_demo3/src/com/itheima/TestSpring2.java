package com.itheima;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//下面的两个注解用于替代ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
@RunWith(SpringJUnit4ClassRunner.class)//注意有.class
@ContextConfiguration(classes=SpringConfiguration.class)//该方法不再需要applicationContext.xml;
public class TestSpring2 {
	
	//既然没有了AnnotationConfigApplicationContext(SpringConfiguration.class)，自然也就没有getBean方法了，所以只能用成员变量
	@Autowired
	private CustomerService customerserviceimpl;
	
	@Test
	public void test2(){
		customerserviceimpl.addCustomer();
	}
}
