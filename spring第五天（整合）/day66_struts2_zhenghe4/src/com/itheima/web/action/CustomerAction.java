package com.itheima.web.action;

import com.opensymphony.xwork2.ActionSupport;
//本项目是一个整合前的单独的struts2框架
public class CustomerAction extends ActionSupport {
	
	public String list() {
		System.out.println("list方法执行了");
		return null;
	}
	
}
