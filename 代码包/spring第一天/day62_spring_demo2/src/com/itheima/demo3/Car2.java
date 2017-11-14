package com.itheima.demo3;

public class Car2 implements Car {
	private String name;
	private Double price;
	
	//只有构造函数没有set方法
	public Car2(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
		System.out.println("品牌是"+name+"价格是"+price);
	}
	
}
