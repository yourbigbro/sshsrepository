package com.itheima.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils 
{
	static EntityManagerFactory factory = null;
	static
	{
		//注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
		factory = Persistence.createEntityManagerFactory("aaa");
	}
	
	// 获取连接的方法
	public static EntityManager getEntityManager()
	{
		return factory.createEntityManager();
	}
}
