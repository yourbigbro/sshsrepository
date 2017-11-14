package com.itheima.demo3;

public class Car3 implements Car {
	private String name;
	private Double price;
	
	//只有set方法
	
	public void setName(String name) {
		this.name = name;
		System.out.println("品牌是"+name);
	}
	public void setPrice(Double price) {
		this.price = price;
		System.out.println("价格是"+price);
	}
	
}

