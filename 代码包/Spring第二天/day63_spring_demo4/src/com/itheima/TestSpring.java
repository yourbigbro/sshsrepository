package com.itheima;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
	
	@Test
	public void test1(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		//执行Spring容器中的对象中的方法，以获得DataSource对象
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		System.out.println(dataSource);
	}
}
