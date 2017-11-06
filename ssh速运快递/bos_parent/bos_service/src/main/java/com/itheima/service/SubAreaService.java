package com.itheima.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.SubArea;

public interface SubAreaService {

	List<SubArea> findAll();

	Page<SubArea> findAll(Pageable pageable);

	void save(SubArea model);

	List<SubArea> findByFixedAreaIsNull();

	List<SubArea> findByFixedAreaIsSomeone(String id);

	SubArea findOne(String string);

	void removeRelationship(String idss);

	List<Object[]> listPie();

}
