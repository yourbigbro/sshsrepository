package com.itheima.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.itheima.dao.ICustomerDao;
import com.itheima.domain.Customer;
import com.itheima.utils.JPAUtils;

public class CustomerDaoImpl implements ICustomerDao {

	@Override
	public List<Customer> findAll() {
		//用了JPA注解之后就不能再用下面的方法开启连接和事务
		/*Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();*/
		//创建数据库连接
		EntityManager em = JPAUtils.getEntityManager();
		//获得事务。JPA方法的连接不能直接开启事务，必须先获取事务再开启事务
		EntityTransaction transaction = em.getTransaction();
		//开启事务
		transaction.begin();
		
		//注意，createQuery中传入的不是数据库表的名称而应该是类的名称(Customer，并且注意大写)
		//注意查询之后得到结果之后要调用list()方法
		List<Customer> list=em.createQuery("select c from Customer c").getResultList();
		transaction.commit();
		em.close();
		return list;
	}

	@Override
	//从数据库中查找某一id值的客户对象Customer
	public Customer find(String custId) {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		//注意属性值也不再是数据库表的列名而是Customer对象的成员变量的名字
		Query query = em.createQuery("select c from Customer c where custId=?");
		//注意JPA中的索引是从1开始的而不是0
		//注意从request域对象中获得的是字符串，应该转化成Long类型以符合主键的要求
		query.setParameter(1, Long.parseLong(custId));
		List<Customer> list = query.getResultList();
		transaction.commit();
		em.close();
		//返回Customer对象
		return list.get(0);
		
	}
	//根据edit.jsp中的信息修改数据库
	@Override
	public void editCustomer(Customer customer) {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		//找到要修改(删除)的项目
		System.out.println(customer.getCustId());
		
		Customer customer2=em.find(Customer.class, customer.getCustId());
		System.out.println(customer2.getCustPhone());
		
		//直接将原来的对象(记录行)整个移除
		em.remove(customer2);
		//将新的信息添加进来(不能用persist只能用merge，否则会报错)
		em.merge(customer);
		transaction.commit();
		
		em.close();
		
	}

}
