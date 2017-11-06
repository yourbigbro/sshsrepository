package com.itheima.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itheima.service.AccountService;

//该demo只是比demo2多了事务的配置。在applicationContext.xml里面。其他的完全相同
public class TestTransfer {
	
	//注意，当测试类的类名是Test时无法添加这个注解
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//这里用到了泛型，因为强制转换成了接口的类型
		AccountService accountService = (AccountService)ac.getBean("accountService");
		//注意，1000f表示1000是Float类型，否则不加的话它就是int类型
		accountService.transfer("张三", "李四", 1000f);
	}
}
