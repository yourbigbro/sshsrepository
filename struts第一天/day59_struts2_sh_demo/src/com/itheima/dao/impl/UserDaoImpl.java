package com.itheima.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.itheima.dao.inter.IUserDao;
import com.itheima.domain.Customer;
import com.itheima.utils.JPAUtils;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<Customer> query() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		List<Customer> list = em.createQuery("select c from Customer c").getResultList();
		trans.commit();
		em.close();
		return list;
	}

}
