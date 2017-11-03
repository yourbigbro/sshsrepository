package com.itheima.exception;

//自定义异常
public class MyException extends Exception {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//空参构造函数
	public MyException() {
		super();
	}
	
	//满参构造函数
	public MyException(String message) {
		super();
		this.message = message;
	}

	
}
