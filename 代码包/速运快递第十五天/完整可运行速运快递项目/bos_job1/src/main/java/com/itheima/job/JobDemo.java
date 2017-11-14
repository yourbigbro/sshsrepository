package com.itheima.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobDemo {

	public void myJob(){
		//注意，月是大写的MM，分钟是小写的mm
		System.out.println("当前时间是"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	public static void main(String[] args) {
		//当加载spring配置文件的时候，定时任务就会开启并执行，我们不开启项目，所以在这里手动加载配置文件以使得配置文件被加载
		new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
}
