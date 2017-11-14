package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.StandardDao;
import com.itheima.domain.Standard;
import com.itheima.service.StandardService;
@Service
//添加事务用的注解
@Transactional
public class StandardServiceImpl implements StandardService {
	
	//注意，即使dao层没有注解这里也有注解
	//@Autowired是spring的注解，@Resource则是jdk的注解。后者是固定的，找不到就会报错；
	//前者会按照接口，类的顺序去找，找不到就返回null而不是报错。而@Resource只要找不到值就会报错
	@Autowired(required=false)
	private StandardDao standardDao;

	//查找所有对象
	@Override
	//注意，不用再手动调用dao层了
	public List<Standard> findAll() {
		return standardDao.findAll();
	}

	//保存某个对象
	@Override
	public void save(Standard standard) {
		//注意，保存是没有返回值的
		standardDao.save(standard);
		
	}

	@Override
	public Page<Standard> queryAllByPagenation(Pageable pb) {
		return standardDao.findAll(pb);
	}
}
