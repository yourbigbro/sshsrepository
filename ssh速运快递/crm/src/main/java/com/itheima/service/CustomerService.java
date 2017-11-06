package com.itheima.service;

import java.util.List;

import javax.jws.WebService;

import com.itheima.crm.Customer;

//注意webservice注解必须放在接口上而不能放在实现类上
@WebService
public interface CustomerService {

	public List<Customer> findAll();
	
	public List<Customer> findByFixedAreaIdIsNull();
	
	public List<Customer> findByFixedAreaId(String fixedAreaId);
	
	public void setFixedAreaIdNull(List<Customer> list);
	
	public void changeFixedAreaIdById(String theFixedAreaId, Integer id);
	
	public void save(Customer customer);
	
	public void setEmail(String email,String telephone);
	
	public List<Customer> findByAddress(String address);
	
	public Customer findByTelephoneAndPassword(String telephone,String password);
}
