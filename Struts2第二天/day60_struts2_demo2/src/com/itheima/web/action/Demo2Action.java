package com.itheima.web.action;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class Demo2Action extends ActionSupport {
	private User user;
	
	public String execute(){
		System.out.println(user);
		return null;
	}
	public User getUser(){
		System.out.println("获得user对象");
		return user;
	}
	public void setUser(User user){//这里不传参数的话编译器不会报错，但是运行时会报错
		System.out.println("设置user对象");
		this.user=user;
	}
}
