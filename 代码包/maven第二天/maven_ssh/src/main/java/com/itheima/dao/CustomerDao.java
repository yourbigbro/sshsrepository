package com.itheima.dao;

import com.itheima.domain.Customer;

public interface CustomerDao {

	Customer findCustById(Long custId);

}
