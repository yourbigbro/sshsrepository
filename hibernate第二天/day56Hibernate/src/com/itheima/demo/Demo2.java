package com.itheima.demo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.HibernateUtils;

public class Demo2 {
	//批量查询的第一种方式：HQL查询(Query方式)
	@Test
	public void t1(){
		Session session = HibernateUtils.getCurentSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("from Customer");//注意是Customer而不是表的名字
		List<Customer> list = qr.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}
	//条件查询
	@Test
	public void t2(){
		Session session = HibernateUtils.getCurentSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("from Customer where cust_name=?");
		qr.setParameter(0, "你爹");
		List<Customer> list = qr.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}
	//分页查询
	@Test
	public void t3(){
		Session session = HibernateUtils.getCurentSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("from Customer");
		qr.setFirstResult(0);//索引从零开始
		qr.setMaxResults(2);//每页的数量
		List<Customer> list = qr.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}
	//单个属性查询
	@Test
	public void t4(){
		Session session = HibernateUtils.getCurentSession();
		Transaction tx = session.beginTransaction();
		Query qr = session.createQuery("select cust_name from Customer");
		List<Object> list = qr.list();//集合中的元素是对象
		for (Object object : list) {
			System.out.println(object);
		}
		tx.commit();
	}
		//多个属性查询
		@Test
		public void t5(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Query qr = session.createQuery("select cust_id,cust_name from Customer");//中间用逗号间隔
			List<Object[]> list = qr.list();//集合中的元素是对象组成的数组
			for (Object[] object : list) {
				System.out.println(Arrays.toString(object));//将数组转化成字符串
			}
			tx.commit();
		}
		//对查询出的结果进行排序
		@Test
		public void t6(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Query qr = session.createQuery("from Customer order by cust_id desc");//中间用逗号间隔。desc是从大到小，不加desc是从小到大
			List<Customer> list = qr.list();//集合中的元素是对象组成的数组
			for (Customer object : list) {
				System.out.println(object);//将数组转化成字符串
			}
			tx.commit();
		}
		//聚合查询(count,max,min,avg)
		@Test
		public void t7(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Query qr = session.createQuery("select avg(cust_id) from Customer");//中间用逗号间隔。desc是从大到小，不加desc是从小到大
			Object ur = qr.uniqueResult();//获取唯一值
			System.out.println(ur);
			tx.commit();
		}
		//批量查询的第二种方式(Criteria方式)，QBC查询，完全面向对象，因为没有SQL语句
		//聚合查询(count,max,min,avg)
		@Test
		public void t8(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Criteria iter = session.createCriteria(Customer.class);//中间用逗号间隔。desc是从大到小，不加desc是从小到大
			List<Customer> list = iter.list();//同HQL方法相同
			for (Customer object : list) {
				System.out.println(object);
			}
			tx.commit();
		}
		//条件查询
		@Test
		public void t9(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Criteria iter = session.createCriteria(Customer.class);
			iter.add(Restrictions.like("cust_name", "%爹%"));
			List<Customer> list = iter.list();
			for (Customer object : list) {
				System.out.println(object);
			}
			tx.commit();
		}
		//分页查询，同HQL的分页查询相同
		@Test
		public void t10(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Criteria iter = session.createCriteria(Customer.class);//注意参数不是字符串
			iter.setFirstResult(0);//索引从零开始
			iter.setMaxResults(2);//每页的数量
			List<Customer> list = iter.list();
			for (Customer customer : list) {
				System.out.println(customer);
			}
			tx.commit();
		}
		//排序
		@Test
		public void t11(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Criteria iter = session.createCriteria(Customer.class);
			iter.addOrder(Order.desc("cust_id"));//从大到小排列
			List<Customer> list = iter.list();//排序完了还是原来那么遍历和打印
			for (Customer object : list) {
				System.out.println(object);
			}
			tx.commit();
		}
		//聚合
		@Test
		public void t12(){
			Session session = HibernateUtils.getCurentSession();
			Transaction tx = session.beginTransaction();
			Criteria iter = session.createCriteria(Customer.class);
			//Criteria对象上输入属性字段是因为上一行的参数只能是Customer.class，所以只能在这一行输入成员变量，和HQL不同。因此也就比HQL多一行
			iter.setProjection(Projections.avg("cust_id"));//注意后面是Projections而非Projection
			Object ur = iter.uniqueResult();//这里和HQL一样
			System.out.println(ur);
			tx.commit();
		}
}
