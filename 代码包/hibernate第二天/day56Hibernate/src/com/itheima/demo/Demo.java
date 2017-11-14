package com.itheima.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;

public class Demo {
	//测试瞬时态，持久态和脱管态
	@Test
	public void t1(){
		//从连接池中获得连接
		Session session = HibernateUtils.openSession();
		//开启事务(开启事务是在连接上开启，因此要先从连接池中取出连接再开启事务)
		Transaction tx = session.beginTransaction();
		//创建瞬时态的持久化，既没有OID属性，也没有被session管理
		Customer customer = new Customer();
		customer.setCust_name("jack");
		//创建持久态的持久化类，有OID属性，并且被session管理
		session.save(customer);
		tx.commit();
		//关闭连接之后创建脱管态的持久化类，有OID属性，但是没有被session管理
		session.close();
		System.out.println(customer);
	}
	//持久态的持久化类会自动更新数据库，而不用提交。从session里获得的对象就是持久态的持久化类
	@Test
	public void t2(){
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		//获得持久化的持久化类
		Customer customer = session.get(Customer.class, 2L);//2L指的是id，并且id的数据类型是Long
		//修改持久化的持久化类
		customer.setCust_name("你爹");
		//完成操作之后提交事务
		tx.commit();
		//提交事务之后关闭连接，此时会更新数据库。因为关闭连接的时候会清空session一级缓存
		session.close();
	}
	//
}
