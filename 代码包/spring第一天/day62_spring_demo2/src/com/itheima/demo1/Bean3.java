package com.itheima.demo1;

public class Bean3 {

	public Bean3() {
		System.out.println("我是构造函数");
	}
	public void init(){
		System.out.println("我是初始化时执行的函数");
	}
	
	public void close(){
		System.out.println("我是销毁时执行的函数");
	}
}
