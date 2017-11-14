package com.itheima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//只有在pom文件里关联了com.itheima.crm项目到crm项目才能够引用，否则不能引用
import com.itheima.crm.Customer;

//注意，所有的查询数据库在jpa里面都不用写sql语句，所有的增删改都需要写sql语句
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	List<Customer> findByFixedAreaIdIsNull();

	List<Customer> findByFixedAreaId(String fixedAreaId);

	//注意两点，第一，增删改用的竟然是query，第二，要有modifying注解，作用是提交事务。不过提交事务竟然放到了dao层
	@Query("update Customer set fixedAreaId=null where fixedAreaId=?")
	@Modifying
	void setFixedAreaIdNull(String fixedAreaId);

	@Query("update Customer set fixedAreaId=? where id=?")
	@Modifying
	void changeFixedAreaIdById(String theFixedAreaId, Integer id);

	@Query("update Customer set email=? where telephone=?")
	@Modifying
	void setEmailByTelephone(String email, String telephone);

	//只要不是按照主键查询的话返回值就必然是对象的集合
	List<Customer> findByAddress(String address);

	Customer findByTelephoneAndPassword(String telephone, String password);

}
