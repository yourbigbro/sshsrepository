package com.itheima.action;

import com.opensymphony.xwork2.Action;

public class Demo2Action implements Action{

	@Override
	public String execute() throws Exception {
		System.out.println("第二种方法执行成功");
		return SUCCESS;
	}
	
}
