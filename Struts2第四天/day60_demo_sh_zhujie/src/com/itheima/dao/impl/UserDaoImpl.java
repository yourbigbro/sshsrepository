package com.itheima.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.itheima.dao.IuserDao;
import com.itheima.domain.User;
import com.itheima.utils.JPAUtils;

public class UserDaoImpl implements IuserDao {

	@Override
	public User checkUser(User user) {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query query = em.createQuery("select u from User u where username=? and password=?");
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		query.setParameter(1, username);
		query.setParameter(2, password);
		User user2;
		try {
			//当无法从数据库查到信息时，不会返回null而是会报错
			user2 = (User)query.getSingleResult();
		} catch (Exception e) {
			//捕获错误信息并且显示地将user2赋值为null
			transaction.commit();
			em.close();
			user2=null;
			return user2;
		}
		
		transaction.commit();
		em.close();
		return user2;
	}

}
