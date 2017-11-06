package com.itheima.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.rowmapper.MyRowMapper;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
	
	//用于更新数据库
	@Override
	public void updateAccount(Account a) {
		this.getJdbcTemplate().update("update account set money=? where name=?",a.getMoney(),a.getName());

	}
	
	//用于查询数据库
	@Override
	public Account findAccount(String name) {
		//注意，返回的是集合，但是只取第一项
		return this.getJdbcTemplate().query("select * from account where name=?",new MyRowMapper(),name).get(0);
	}

}
