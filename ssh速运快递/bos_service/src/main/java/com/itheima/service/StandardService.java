package com.itheima.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.Standard;

public interface StandardService {

	List<Standard> findAll();

	void save(Standard standard);

	Page<Standard> queryAllByPagenation(Pageable pb);

}
