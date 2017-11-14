package com.itheima.domain;

//谁的信息许多就继承谁，因为可以少写几个成员变量和set/get方法
//之所以只继承了Orders没有继承User是因为java只支持单继承，所以只能挑选一个进行继承
public class OrdersUser extends Orders{

	private String username;
	
	private String address;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
