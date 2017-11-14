package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.TakeTimeDao;
import com.itheima.domain.TakeTime;
import com.itheima.service.TakeTimeService;
@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {

	@Autowired
	private TakeTimeDao takeTimeDao;

	//该方法用于找到所有的时间段
	@Override
	public List<TakeTime> findAll() {
		return takeTimeDao.findAll();
	}

	//根据id找到TakeTime对象
	@Override
	public TakeTime findOne(Integer takeTimeId) {
		return takeTimeDao.findOne(takeTimeId);
	}
}
