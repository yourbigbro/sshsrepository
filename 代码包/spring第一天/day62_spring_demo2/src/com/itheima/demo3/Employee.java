package com.itheima.demo3;

public class Employee {
	private String name;
	private Car car;
	public void setName(String name) {
		this.name = name;
		System.out.println("employee中的set方法执行了");
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
}
