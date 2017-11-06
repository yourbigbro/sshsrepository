package com.itheima.service;

import java.util.ArrayList;
import java.util.List;

import com.itheima.domain.Area;

//注意cxf的注解是@WebService而不是@Service。后者是spring的注解
public interface AreaService {

	void saveXls(ArrayList<Area> arrayList);
	
	List<Area> listName();

	Area findByProvinceAndCityAndDistrict(String province, String city, String district);

}
