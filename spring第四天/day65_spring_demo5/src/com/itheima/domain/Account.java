package com.itheima.domain;

public class Account {
	//用户id(主键)
	private int id;
	//用户姓名
	private String name;
	//用户存款(注意是float类型)
	private float money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
	//序列化只是为了方便看结果
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", money=" + money + "]";
	}
	
	
	
}
