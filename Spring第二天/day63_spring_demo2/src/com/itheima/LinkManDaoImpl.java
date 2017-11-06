package com.itheima;

import org.springframework.stereotype.Repository;

@Repository("linkmandaoimpl")
public class LinkManDaoImpl implements CustomerDao {

	@Override
	public void addCustomer() {
		System.out.println("linkmandaoimpl执行了");

	}

}
