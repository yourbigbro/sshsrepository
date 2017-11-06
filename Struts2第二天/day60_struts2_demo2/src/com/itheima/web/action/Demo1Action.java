package com.itheima.web.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
//这五个demo都要继承ActionSupport类
public class Demo1Action extends ActionSupport{
	//不需要构造函数
	private String username;
	private String password;
	private String sex;
	private String hobby;
	private Date birthday;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	} 
	public String execute(){
		System.out.println(username);
		System.out.println(password);
		System.out.println(sex);
		System.out.println(hobby);
		System.out.println(birthday);
		return null;
	}
}
