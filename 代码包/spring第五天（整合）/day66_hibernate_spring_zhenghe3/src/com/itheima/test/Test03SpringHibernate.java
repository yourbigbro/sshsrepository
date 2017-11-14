package com.itheima.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

	//本项目是spring和hibernate的整合

	//下面的两个注解是注解用的东西
	@RunWith(SpringJUnit4ClassRunner.class)//作用是開啓spring容器,這樣以來配置的bean元素就全部被实例化了
	@ContextConfiguration("classpath:applicationContext.xml")
	public class Test03SpringHibernate {
		
		@Autowired//只要将spring容器抽取出去了，该注解就是必须要有的，而不是有注解就可以没有配置文件。有注解就没有配置文件那是针对类名上的注解，而不是变量上的注解
		private CustomerService customerService;//该变量能够使用就是因为开启了spring容器
		
		//查找方法
		@Test
		public void testFindAll(){
			List<Customer> list = customerService.findAllCustomer();
			for(Object o : list){
				System.out.println(o);
			}
		}
		
		//保存方法
		@Test
		public void testSave(){
			Customer c = new Customer();
			c.setCustName("传智学院test");		
			customerService.saveCustomer(c);
		}
}
