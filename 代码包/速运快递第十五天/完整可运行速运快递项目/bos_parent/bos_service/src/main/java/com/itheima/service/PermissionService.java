package com.itheima.service;

import java.util.List;

import com.itheima.system.Permission;

public interface PermissionService {

	List<Permission> findAll();

	void save(Permission model);

}
