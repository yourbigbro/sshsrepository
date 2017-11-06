package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

// 角色
public class Role 
{
	private Long role_id;// 主键ID
	private String role_name;// '角色名称',
	private String role_memo;// '备注',
	// 有用户的集合，前面是set后面是hashset
	private Set<User> users=new HashSet<User>();
	
	
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_memo() {
		return role_memo;
	}
	public void setRole_memo(String role_memo) {
		this.role_memo = role_memo;
	}
	
	
}
