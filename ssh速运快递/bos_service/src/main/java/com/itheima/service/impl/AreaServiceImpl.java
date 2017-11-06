package com.itheima.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.AreaDao;
import com.itheima.domain.Area;
import com.itheima.service.AreaService;
//下面这两个都是spring的注解
@Service("areaService")
@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaDao areaDao;
	
	//该方法用于将excel表中的每一行数据保存到数据库中
	@Override
	public void saveXls(ArrayList<Area> arrayList) {
		//注意，AreaDao依然是只有接口没有实现类，并且接口继承另外两个接口，并且dao层的接口上面没有注解
		areaDao.save(arrayList);
	}
	
	//从Area中查询name属性
		@Override
		public List<Area> listName() {
			//这里之所以可以默认查询Area对象是因为areaDao里面有jpa中的泛型是Area
			return areaDao.findAll();
		}

		//根据省市区查询对应的区域对象
		@Override
		public Area findByProvinceAndCityAndDistrict(String province, String city, String district) {
			return areaDao.findByProvinceAndCityAndDistrict(province, city, district);
		}

}
