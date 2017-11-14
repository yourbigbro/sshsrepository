package com.itheima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.system.Menu;

public interface MenuDao extends JpaRepository<Menu, Integer>{

	List<Menu> findAll();

	List<Menu> findByParentMenuIsNull();

	@Query("select m from Menu m inner join m.roles r inner join r.users u where u.id=?")
	List<Menu> findByUser(Integer id);

}
