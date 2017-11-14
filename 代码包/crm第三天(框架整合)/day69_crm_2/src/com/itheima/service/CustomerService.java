package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;
import com.itheima.web.action.PageBean;

public interface CustomerService {

	List<BaseDict> findCustSource();

	List<BaseDict> findCustLevel();

	void saveCustomer(Customer customer);

	List<Customer> listAllCustomer();

	Customer queryByCustId(Integer custId);

	void reSaveCustomer(Customer customer);

	void removeCustomer(Customer customer);

	List<Customer> findCustomersByCriteria(DetachedCriteria criteria);

	PageBean<Customer> listCurrentPageCustomer(DetachedCriteria criteria, int currentPage, int pageSize);

}
