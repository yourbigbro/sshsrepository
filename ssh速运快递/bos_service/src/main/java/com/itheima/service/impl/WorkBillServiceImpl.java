package com.itheima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.WorkBillDao;
import com.itheima.domain.WorkBill;
import com.itheima.service.WorkBillService;
@Service("workBillService")
@Transactional
public class WorkBillServiceImpl implements WorkBillService {
	
	@Autowired
	private WorkBillDao workBillDao;

	@Override
	public void save(WorkBill workBill) {
		workBillDao.save(workBill);

	}

}
