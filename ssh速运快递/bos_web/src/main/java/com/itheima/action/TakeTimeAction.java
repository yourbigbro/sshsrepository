package com.itheima.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.itheima.domain.Courier;
import com.itheima.domain.TakeTime;
import com.itheima.service.TakeTimeService;
import com.itheima.utils.action.common.CommonAction;


public class TakeTimeAction extends CommonAction<Courier>{
	
	@Autowired
	private TakeTimeService takeTimeService;

	//该方法用于找到下拉列表中的所有派送快递时间段
	@Action("findAllTime")
	public String findAllTime(){
		List<TakeTime> list=takeTimeService.findAll();
		this.java2json(list, null);
		return NONE;
	}
}
