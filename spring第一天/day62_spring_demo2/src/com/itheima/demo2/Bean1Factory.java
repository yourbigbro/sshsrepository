package com.itheima.demo2;

public class Bean1Factory {
	//静态方法的工具类
	public static Bean1 createBean(){
		System.out.println("Bean1Factory类中的createBean方法执行了");
		return new Bean1();
	}
}
