package com.itheima.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.itheima.domain.Customer;
//hibernate
//该项目的作用是测试hibernate是否能独立运行
//注意本来连接池和事务也没在hibernate的配置文件中出现，只是出现在了dao层代码中
public class Test02Hibernate {
	
		//查询
		@Test
		public void testFindAll(){
			//1.读取配置文件
			Configuration cfg = new Configuration();
			cfg.configure();//看来获得之后还要配置一下
			//2.根据配置文件获取SessionFactory(相当于获取连接池)
			SessionFactory factory = cfg.buildSessionFactory();
			//3.根据SessionFactory获取一个Session(相当于获取连接)
			Session s = factory.getCurrentSession();//在连接上面进行增删改查操作
			//4.开启事务(在连接上面开启事务，因为事务必须是针对的同一连接)
	//其实是在创建事务的同时开启事务
			Transaction tx = s.beginTransaction();
			//5.执行操作(在连接上进行操作而不是在事务上进行操作)
			Query query = s.createQuery("from Customer");//注意他不是直接获得集合
			List list = query.list();
			for(Object o : list){
				System.out.println(o);
			}
			//6.提交事务
			tx.commit();
			//7.释放资源(好像只关闭了连接池并未关闭连接)
			factory.close();//关闭连接池
		}
		
		
		//保存
		@Test
		public void testSave(){
	//从创建对象并设置
			Customer c = new Customer();
			c.setCustName("传智专修学院");
			//1.读取配置文件
			Configuration cfg = new Configuration();
			cfg.configure();
			//2.根据配置文件获取SessionFactory
			SessionFactory factory = cfg.buildSessionFactory();
			//3.根据SessionFactory获取一个Session
			Session s = factory.getCurrentSession();
			//4.开启事务
			Transaction tx = s.beginTransaction();
			//5.执行操作（保存对象）
			s.save(c);
			//6.提交事务
			tx.commit();
			//7.释放资源
			factory.close();
		}
}
