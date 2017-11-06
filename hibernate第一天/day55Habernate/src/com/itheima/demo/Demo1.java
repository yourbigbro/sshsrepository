package com.itheima.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.itheima.domain.Customer;

public class Demo1 {
	//JUnit只需要在@test书写之后就可以自动点击添加
	//添加数据
	@Test
	public void t1(){
		//加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure();
		//开启连接池
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//从连接池中获取连接
		Session session = sessionFactory.openSession();
		//根据连接获取事务
		Transaction transaction = session.beginTransaction();
		//创建新对象并且填充
		Customer customer = new Customer();
		customer.setCust_name("吴林");
		//将创建的对象保存到数据库
		session.save(customer);//////////////////核心
		//提交事务
		transaction.commit();
		//关闭连接
		session.close();
		//关闭连接池
		sessionFactory.close();
	}
	//单条数据的查询
	@Test
	public void t2(){
		//加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure();
		//开启连接池
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//从连接池中获取连接
		Session session = sessionFactory.openSession();
		//根据连接获取事务
		Transaction transaction = session.beginTransaction();
		//查询
		Customer load = session.load(Customer.class, 1L);///////////////////核心
		System.out.println(load);//只要Customer类中实现了toString方法就不必再调用toString
		//提交事务
		transaction.commit();
		//关闭连接
		session.close();
		//关闭连接池
		sessionFactory.close();
	}
		//更改(先查询再更改)
		@Test
		public void t3(){
			//加载配置文件
			Configuration configuration = new Configuration();
			configuration.configure();
			//开启连接池
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			//从连接池中获取连接
			Session session = sessionFactory.openSession();
			//根据连接获取事务
			Transaction transaction = session.beginTransaction();
			//查询得到对象
			Customer get = session.get(Customer.class, 1L);///////////////////核心。注意只能用get不能用load。
			//更改对象
			get.setCust_name("呵呵");
			//修改数据库
			session.update(get);
			//提交事务
			transaction.commit();
			//关闭连接
			session.close();
			//关闭连接池
			sessionFactory.close();
		}
		//删除(先查询(得到)再删除)
		@Test
		public void t4(){
			//加载配置文件
			Configuration configuration = new Configuration();
			configuration.configure();
			//开启连接池
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			//从连接池中获取连接
			Session session = sessionFactory.openSession();
			//根据连接获取事务
			Transaction transaction = session.beginTransaction();
			//查询得到对象
			Customer load = session.get(Customer.class, 1L);///////////////////核心。注意只能用get不能用load。
			//删除对象
			session.delete(load);
			//提交事务
			transaction.commit();
			//关闭连接
			session.close();
			//关闭连接池
			sessionFactory.close();
		}
}
