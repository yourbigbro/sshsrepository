package com.itheima;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")//该方法需要配置文件，只是不用new ClassPathXmlApplicationContext("applicationContext.xml");
//上面classpath的作用：程序运行时默认会到WEB-INF文件夹下面寻找applicationContext.xml文件，加上classpath之后就会到src目录下面寻找applicationContext.xml文件
public class TestSpring3 {
	@Autowired
	private CustomerService customerserviceimpl;
	
	@Test
	public void test3(){
		customerserviceimpl.addCustomer();
	}
}
