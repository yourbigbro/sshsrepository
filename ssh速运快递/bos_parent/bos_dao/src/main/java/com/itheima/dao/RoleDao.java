package com.itheima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.system.Role;

public interface RoleDao extends JpaRepository<Role, Integer>{
	@Query("select r from Role r inner join r.users u where u.id = ? ")
	List<Role> findByUser(Integer id);

}
