package com.itheima.web.action;

import java.util.List;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class Demo4Action extends ActionSupport{
	private List<User> list;
	
	public List<User> getList(){
		return list;
	}
	public void setList(List<User> list){
		this.list=list;
	}
	public String execute(){
		for (User user : list) {
			System.out.println(user);
		}
		return null;
	}
}
