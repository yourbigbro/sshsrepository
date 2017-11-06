package com.itheima.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.rowmapper.MyRowMapper;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {//该类不再继承JdbcDaoSupport类，因为不显式写出jdbcTemplate的话就无法对它进行注解
	
	//声明jdbc模板并进行赋值
	//注解和xml配置的区别就在于有没有set方法，xml文件里面没有任何区别
	//另外要区分类的注解和成员变量的注解，前者用注解就不用写配置文件里的bean元素，后者依然要写bean元素，只是不用写set方法
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//用于更新数据库
	@Override
	public void updateAccount(Account a) {
		jdbcTemplate.update("update account set money=? where name=?",a.getMoney(),a.getName());

	}
	
	//用于查询数据库
	@Override
	public Account findAccount(String name) {
		//注意，返回的是集合，但是只取第一项
		return jdbcTemplate.query("select * from account where name=?",new MyRowMapper(),name).get(0);
	}

}
