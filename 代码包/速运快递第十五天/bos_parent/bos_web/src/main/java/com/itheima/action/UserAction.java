package com.itheima.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.service.UserService;
import com.itheima.system.User;
import com.itheima.utils.action.common.CommonAction;


public class UserAction extends CommonAction<User>{

	@Autowired
	private UserService userService;
	
	//该方法用于展示所有的用户信息
	//假如不跳转页面，而只是返回原来的页面的话就写NONE，而不写SUCCESS
	@Action("userList")
	public String userList(){
		
		List<User> list=userService.findAll();
		this.java2json(list, new String[]{"roles"});
		return NONE;
	}
	
	//从前台接收复选框的角色信息
	private String[] roleIds;
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	//该方法用于保存新的用户(保存完之后就跳转到展示页面展示更新后的数据)
	@Action(value="saveUser",results={@Result(name="success",type="redirect",location="/pages/system/userlist.html")})
	public String saveUser(){
		
		userService.save(model,roleIds);
		return SUCCESS;
	}
}
