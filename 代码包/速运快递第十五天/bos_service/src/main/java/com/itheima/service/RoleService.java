package com.itheima.service;

import java.util.List;

import com.itheima.system.Role;

public interface RoleService {

	List<Role> findAll();

	void save(Role model);

	void saveRole(Role model, Integer[] permissionIds, String functionIds);

}
