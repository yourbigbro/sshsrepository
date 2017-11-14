package com.itheima.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.FixedArea;


public interface FixedAreaService {

	void save(FixedArea model);

	Page<FixedArea> find(Pageable pageable);

	FixedArea findOne(String id);

	void fixedArea_associationCourierToFixedArea(Integer courierId, Integer takeTimeId, String id);

	void subarea(String id, String[] customerIds);

}
