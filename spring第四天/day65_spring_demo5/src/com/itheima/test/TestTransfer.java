package com.itheima.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.config.SpringConfiguration;
import com.itheima.service.AccountService;

//该项目为全注解，没有配置文件，这样做是不好的，因为连接池又重新耦合了(在SpringConfiguration.java中)

//配置文件要做的是扫描包，测试类要做的是关联配置文件

//注意，两个注解里面都是.class字节码文件
@RunWith(SpringJUnit4ClassRunner.class)//创建spring容器
@ContextConfiguration(classes=SpringConfiguration.class)//关联所需配置文件
//该demo只为service和dao加了注解。并没有为事务配置注解，与demo3极为相似
public class TestTransfer {
	
	@Autowired
	private AccountService as;
	
	//注意，当测试类的类名是Test时无法添加这个注解
	@Test
	public void test(){
		//注意，1000f表示1000是Float类型，否则不加的话它就是int类型
		as.transfer("张三", "李四", 1000f);
	}
}
