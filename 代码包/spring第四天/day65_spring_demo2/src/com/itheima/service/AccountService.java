package com.itheima.service;

public interface AccountService {
	
	//该方法用于转账，第一个从参数是汇款人，第二个参数是收款人，第三个参数是汇款金额
	public void transfer(String name1,String name2,Float money);
}
