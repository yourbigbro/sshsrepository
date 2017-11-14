package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CourierDao;
import com.itheima.domain.Courier;
import com.itheima.service.CourierService;

@Service("courierService")
@Transactional
public class CourierServiceImpl implements CourierService {

	@Autowired
	private CourierDao courierDao;
	
	//从数据库中查询出所有的快递员信息
	@Override
	public Page<Courier> findAll(Pageable pageable) {
		return courierDao.findAll(pageable);
	}

	@Override
	public void saveCourier(Courier courier) {
		courierDao.save(courier);
	}

	@Override
	public void updateDeltag(Integer valueOf) {
		courierDao.updateDeltag(valueOf);
	}

	@Override
	public Page<Courier> findAll(Specification<Courier> spe, Pageable pageable) {
		return courierDao.findAll(spe,pageable);
	}

	@Override
	public List<Courier> findAll() {
		return courierDao.findAll();
	}

	@Override
	public Courier findOne(Integer courierId) {
		return courierDao.findOne(courierId);
	}

}
