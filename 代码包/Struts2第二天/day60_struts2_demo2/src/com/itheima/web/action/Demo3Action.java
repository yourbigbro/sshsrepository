package com.itheima.web.action;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Demo3Action extends ActionSupport implements ModelDriven<User> {
	//创建新的user对象
	private User user=new User();
	//获得user对象
	public User getModel(){
		return user;
	}
	public String execute() {
		System.out.println(user);
		return null;//不能返回配置文件里面没有的状态，否则会报错
	}
}
