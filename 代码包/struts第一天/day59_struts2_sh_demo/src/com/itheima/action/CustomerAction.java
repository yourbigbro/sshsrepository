package com.itheima.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.Customer;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.service.inter.IUserService;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport{//注意此处只能继承不能实现接口

	
	public String user() throws Exception {
		IUserService user=new UserServiceImpl();
		List<Customer> list=user.query();
		//保存list到request域对象中
		ServletActionContext.getRequest().setAttribute("list", list);
		return "success";
	}
}
