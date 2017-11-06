package com.itheima.demo1;

public class AdminUserDaoImpl implements UserDao{
	private String hehe;
	
	
	public void setHehe(String hehe) {
		this.hehe = hehe;
	}


	@Override
	public void insert() {
		System.out.println(hehe+"管理员插入运行了");
	}

}
