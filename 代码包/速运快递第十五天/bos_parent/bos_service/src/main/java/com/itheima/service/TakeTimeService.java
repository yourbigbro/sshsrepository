package com.itheima.service;

import java.util.List;

import com.itheima.domain.TakeTime;

public interface TakeTimeService {

	List<TakeTime> findAll();

	TakeTime findOne(Integer takeTimeId);

}
