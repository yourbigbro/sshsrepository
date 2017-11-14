package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.itheima.domain.Area;

public interface AreaDao extends JpaRepository<Area, String>,JpaSpecificationExecutor<Area>{

	Area findByProvinceAndCityAndDistrict(String province, String city, String district);

}
