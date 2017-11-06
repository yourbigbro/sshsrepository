package com.itheima.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils
{
	static Configuration config = null;
	static SessionFactory factory = null;
	static
	{
		
		config = new Configuration();
		config.configure();
		
		factory = config.buildSessionFactory();
	}
	
	
	public static Session  openSession()
	{
		
		return factory.openSession();
	}
	
	
	public static Session getCurrentSession()
	{
		
		return factory.getCurrentSession();
	}
}
