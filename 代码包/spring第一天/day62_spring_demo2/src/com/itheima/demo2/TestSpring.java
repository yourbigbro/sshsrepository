package com.itheima.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	@Test
	public void test4(){
		//创建Spring容器
		ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
		//不必写下面这段，因为配置文件中已经设置了自动调用类中的方法，并且会将bean.xml中的全部对应类中的对应方法都执行
		/*//创建Bean1Factory对象并自动调用里面的方法
		Bean1Factory bean = (Bean1Factory) ac.getBean("bean1");*/
	}
	
	@Test
	public void test5(){
		//创建Spring容器
		ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
		
	}
}
