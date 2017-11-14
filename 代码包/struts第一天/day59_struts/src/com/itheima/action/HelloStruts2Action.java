package com.itheima.action;//包的名字必须是action，这是struts2框架规定的

public class HelloStruts2Action {
	//struts2框架规定函数的返回值必须是String
	public String login(){
		System.out.println("login方法成功执行");
		return "success";
	}
}
