package com.itheima.utils;
//注意java项目的lib文件夹中的jar包要配置路径才能生效，和javaee项目中的自动配置不同
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils
{
	static Configuration config = null;
	static SessionFactory factory = null;
	static
	{
		// 加载数据库核心配置文件,只加载一次
		config = new Configuration();
		config.configure();
		// 创建sessionFactory  只创建一次
		factory = config.buildSessionFactory();
	}
	
	// 返回session连接的方法
	public static Session  openSession()
	{
		return factory.openSession();
	}
}
