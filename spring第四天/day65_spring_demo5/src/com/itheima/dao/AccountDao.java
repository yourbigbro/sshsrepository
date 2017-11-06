package com.itheima.dao;

import com.itheima.domain.Account;

public interface AccountDao {

	/**
	 * 修改账户余额(传入整个对象来修改数据库)
	 * @param a
	 */
	public void updateAccount(Account a);
	
	/**
	 * 查询账户信息(传入名字来查询数据库，按理说是应该根据主键id查询数据库)
	 * @param name
	 * @return
	 */
	public Account findAccount(String name);
}
