package com.itheima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.SubArea;

public interface SubAreaDao extends JpaRepository<SubArea, String>{

	//注意，这种查询方式是要在dao层里面定义出来的，但是不用加注解或者写dao层的实现类。只有findAll()这种才不用出现在dao层中
	List<SubArea> findByFixedAreaIsNull();

	//nativeQuery="true"表示用原生sql语句，value属性值是sql语句
	//注意sql语句的最后不能有分号结尾
	@Query(nativeQuery=true,value="select b.c_province,count(1) from t_sub_area a,t_area b where a.c_area_id=b.c_id group by b.c_province")
	List<Object[]> listPie();

}
