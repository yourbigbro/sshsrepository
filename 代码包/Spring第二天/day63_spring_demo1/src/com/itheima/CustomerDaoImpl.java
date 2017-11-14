package com.itheima;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("customerdao")//相当于bean元素的id属性值
@Scope("prototype")
public class CustomerDaoImpl implements CustomerDao {
	
	//用注解为成员变量赋值
	@Value("小明")
	private String name;
	
	@Override
	public void addCustomer() {
		System.out.println(name+"进来了");
	}
	
	@PostConstruct
	public void init(){
		System.out.println("初始化的时候执行");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("销毁的时候执行");
	}
}
