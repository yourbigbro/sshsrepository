package com.itheima.demo2;

public class Bean2Factory {
	//非静态方法的工具类
	public Bean2 createBean(){
		System.out.println("Bean2Factory类中的createBean方法执行了");
		return new Bean2();
	}
}
