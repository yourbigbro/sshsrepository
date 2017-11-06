package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.domain.TakeTime;

//注意只有service层的类名上面有注解
public interface TakeTimeDao extends JpaRepository<TakeTime, Integer>{

}
