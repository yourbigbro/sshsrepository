package com.itheima.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CourierDao;
import com.itheima.dao.FixedAreaDao;
import com.itheima.dao.TakeTimeDao;
import com.itheima.domain.Courier;
import com.itheima.domain.FixedArea;
import com.itheima.domain.SubArea;
import com.itheima.domain.TakeTime;
import com.itheima.service.FixedAreaService;
import com.itheima.service.SubAreaService;

@Service("fixedAreaService")
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
	
	@Autowired
	private FixedAreaDao fixedAreaDao;

	//该方法用于将新添加的定区信息保存到数据库
	@Override
	public void save(FixedArea model) {
		fixedAreaDao.save(model);
	}

	//分页查询
	@Override
	public Page<FixedArea> find(Pageable pageable) {
		//注意是findAll而不是find。毕竟即使是分页查询也是查询了所有
		return fixedAreaDao.findAll(pageable);
	}

	//根据id查找FixedArea对象
	@Override
	public FixedArea findOne(String id) {
		return fixedAreaDao.findOne(id);
	}
	
	//注意，既然将关联业务放在了业务层就不应该再引用service层，而是应该引用dao层
	@Autowired
	private CourierDao courierDao;
	@Autowired
	private TakeTimeDao takeTimeDao;

	//定区关联快递员和配送时间。必须放在service层中，第一是因为业务就应该放在service层中，第二是因为放在action层中涉及到懒加载的问题
	@Override
	public void fixedArea_associationCourierToFixedArea(Integer courierId, Integer takeTimeId, String id) {
		Courier courier=courierDao.findOne(courierId);
		TakeTime takeTime=takeTimeDao.findOne(takeTimeId);
		FixedArea fixedArea=fixedAreaDao.findOne(id);
		
		//关联算是业务，应该放在业务层里，这里放在了表现层，不太好。
		//一对多
		fixedArea.getCouriers().add(courier);
		//一对一
		courier.setTakeTime(takeTime);
		
	}
	
	@Autowired
	private SubAreaService subAreaService;

	//该方法用于在表单提交时将某个或某些分区关联给某个定区
	@Override
	public void subarea(String id, String[] customerIds) {

		//根据定区id获得定区对象(从数据库中查询)
				FixedArea fixedArea = this.findOne(id);
				//遍历属性驱动中的分区id数组查得相应的分区并关联给该定区id对应的定区(fore快捷键会遍历离他最近的数组或者集合，不管该变量是否在当前方法中。)
				for (String string : customerIds) {
					//根据分区id从数据库中查询到分区对象(需要分区的service。引入即可)
					//注意findOne()方法专门用于根据id查找对象。毕竟id是主键，是唯一的
					SubArea sub=subAreaService.findOne(string);
					//将分区关联给定区(先获取集合，而不是上来就setSubareas)
					Set<SubArea> subareas = fixedArea.getSubareas();
					subareas.add(sub);
					//分区关联定区
					fixedArea.setSubareas(subareas);
					//定区关联分区
					sub.setFixedArea(fixedArea);
					System.out.println("完事了");
				}
		
	}

}
