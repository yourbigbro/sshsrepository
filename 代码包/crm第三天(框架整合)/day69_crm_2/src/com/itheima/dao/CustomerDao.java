package com.itheima.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;

public interface CustomerDao {

	List<BaseDict> findCustSource(String string);

	void saveCustomer(Customer customer);

	List<Customer> listAllCustomer();

	Customer queryByCustId(Integer custId);

	void reSaveCustomer(Customer customer);

	void removeCustomer(Customer customer);

	List<Customer> findCustomersByCriteria(DetachedCriteria criteria);

	List<Customer> queryCustomerList(DetachedCriteria criteria, int startIndex, int pageSize);

	List<Long> queryCount(DetachedCriteria criteria);

	/*List<BaseDict> findCustLevel(String string);*/

}
