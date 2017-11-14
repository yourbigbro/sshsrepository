package com.itheima.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	
	static{
		//创建Configuration对象，用于加载主配置文件（hibernate.cfg.xml）
		Configuration cfg = new Configuration().configure();
		//构建一个sessionFactory工厂类 （相当于一个连接池）
		sessionFactory = cfg.buildSessionFactory();
	}
	
	//得到一个连接
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	//从线程局部变量中获得session
	public static Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
}
