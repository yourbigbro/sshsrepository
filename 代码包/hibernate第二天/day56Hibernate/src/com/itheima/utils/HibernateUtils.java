package com.itheima.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration config=null;
	private static SessionFactory factory=null;
	//静态代码块中的代码只在类加载时被执行一次
	static {
		config=new Configuration();
		config.configure();
		factory=config.buildSessionFactory();
	}
	//从连接池中取出session连接
	public static Session openSession(){
		return factory.openSession();
	}
	//获取与当前线程绑定的session
	public static Session getCurentSession(){
		//默认是关闭的，需要在配置文件里面先配置才能使用
		//特性：会自动关闭session连接，手动关闭session连接的话会报错
		return factory.getCurrentSession();
	}
}
