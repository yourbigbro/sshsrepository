package com.itheima.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.service.PermissionService;
import com.itheima.system.Permission;
import com.itheima.utils.action.common.CommonAction;

public class PermissionAction extends CommonAction<Permission>{
	
	@Autowired
	private PermissionService permissionService;

	//该方法用于展示权限列表
	@Action("permissionList")
	public String permissionList(){
		
		List<Permission> list=permissionService.findAll();
		this.java2json(list, new String[]{"roles"});
		return NONE;
	}
	
	//该方法用于添加权限
	//保存之后跳转到展示页面进行数据的重新展示
	@Action(value="permissionAdd",results={@Result(name="success",type="redirect",location="/pages/system/permission.html")})
	public String permissionAdd(){
		
		permissionService.save(model);
		return SUCCESS;
	}
	
}
