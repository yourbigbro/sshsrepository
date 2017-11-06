package com.itheima.web.action;

import com.opensymphony.xwork2.ActionSupport;

//该项目用来演示maven与struts2的结合
public class CustomerAction extends ActionSupport{
	//封装为页面传来的参数
	private String custId;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	public String aaa(){
		System.out.println(custId);
		//SUCCESS是ActionSupport类中的内容，封装了"success"字符串
		return SUCCESS;
	}
	
}
