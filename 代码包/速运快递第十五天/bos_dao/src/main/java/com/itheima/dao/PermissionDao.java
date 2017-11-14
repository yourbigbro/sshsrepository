package com.itheima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.system.Permission;

public interface PermissionDao extends JpaRepository<Permission, Integer>{
	
	@Query("select p from Permission p inner join p.roles r inner join r.users u where u.id = ? ")
	//注意下面不用写成findByUsers，因为上面有注解的话spring data jpa不会看下面的方法名
	List<Permission> findByUser(Integer id);

}
