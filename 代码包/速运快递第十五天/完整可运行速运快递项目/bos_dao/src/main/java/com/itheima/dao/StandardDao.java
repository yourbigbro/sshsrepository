package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.domain.Standard;
//注意，第二个参数是Standard实体类中主键的数据类型
//注意，由于是spring data jpa 而不是hibernate jpa，所以不再加@Repository注解
public interface StandardDao extends JpaRepository<Standard, Integer>{
	
}
