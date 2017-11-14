package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;

public class AccountServiceImpl implements AccountService {
	
	//用配置文件或注解的方式对该变量进行配置。此处我们用的是配置文件
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	//该方法用于转账
	@Override
	public void transfer(String name1, String name2, Float money) {
		//得到汇款方对象
		Account account1 = accountDao.findAccount(name1);
		//得到收款方对象
		Account account2 = accountDao.findAccount(name2);
		
		//对得到的两个对象进行修改
		account1.setMoney(account1.getMoney()-money);
		account2.setMoney(account2.getMoney()+money);
		
		//用修改后的对象修改数据库
		accountDao.updateAccount(account1);
		
		//有了这一句之后事务无法提交，junit会报错
		//int num=10/0;
		
		accountDao.updateAccount(account2);
	}

}
