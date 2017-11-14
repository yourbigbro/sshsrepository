package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.config.SpringConfiguration;
import com.itheima.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringConfiguration.class)//注意用配置类代替xml配置文件之后参数不再是字符串而变成了class字节码文件
public class TestAOP {
	
	@Autowired
	private CustomerService cs;
	
	@Test
	public void test1(){
		cs.addCustomer();
	}
	
	@Test
	public void test2(){
		cs.updateCustomer();
	}
	
	@Test
	public void test3(){
		cs.deleteCustomer();
	}
}
