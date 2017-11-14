package com.itheima.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.domain.Courier;


public interface CourierService {

	Page<Courier> findAll(Pageable pageable);

	void saveCourier(Courier courier);

	void updateDeltag(Integer valueOf);

	Page<Courier> findAll(Specification<Courier> spe, Pageable pageable);
	
	List<Courier> findAll();

	Courier findOne(Integer courierId);

}
