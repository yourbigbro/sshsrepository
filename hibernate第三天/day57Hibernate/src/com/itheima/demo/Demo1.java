package com.itheima.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.domain.LinkMan;
import com.itheima.utils.HibernateUtils;

public class Demo1 {
	@Test
	public void t1(){
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();//开启事务
		Customer customer = new Customer();
		customer.setCust_name("爷");
		LinkMan linkMan = new LinkMan();
		linkMan.setLkm_name("杨幂");
		LinkMan linkMan2 = new LinkMan();
		linkMan2.setLkm_name("柳岩");
		LinkMan linkMan3 = new LinkMan();
		linkMan3.setLkm_name("林志玲");
		customer.getLinkmans().add(linkMan);
		customer.getLinkmans().add(linkMan2);
		customer.getLinkmans().add(linkMan3);
		linkMan.setCustomer(customer);
		linkMan2.setCustomer(customer);
		linkMan3.setCustomer(customer);
		session.save(customer);
		transaction.commit();
		session.close();
		
	}
}
