package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

public class Demo3Action extends ActionSupport{
	public String execute(){
		System.out.println("第三种方法执行成功");
		return SUCCESS;
	}
}
