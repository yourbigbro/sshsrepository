package com.itheima.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itheima.demo2.Bean1;
import com.itheima.demo2.Bean1Factory;

//测试spring
public class TestSpring {
	
	//用Spring
	@Test
	public void test1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
		UserDao udl = (UserDao)ac.getBean("userDao");
		udl.insert();
	}
	
	@Test
	public void test2(){
		//创建Spring容器
		ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
		Bean3 bean1 = (Bean3)ac.getBean("bean3");
		Bean3 bean2 = (Bean3)ac.getBean("bean3");
		//返回true(也就是不会重复取得对象)
		System.out.println(bean1==bean2);
		//不会调用销毁方法，因为ApplicationContext对象没有close方法来销毁容器
	}
	//测试 init-method和destroy-method(了解)
		//destroy-method方法：前提1、使用单例模式(默认就是单例模式，只要不改变默认设置就行) 2、关闭工厂类
	@Test
	public void test3(){
		//创建Spring容器
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
		Bean3 bean1 = (Bean3)ac.getBean("bean3");
		Bean3 bean2 = (Bean3)ac.getBean("bean3");
		//返回true(也就是不会重复取得对象)
		System.out.println(bean1==bean2);
		//会调用销毁时执行的方法(销毁的是Spring容器)
		ac.close();
	}
	
	
}
