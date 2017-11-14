package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.PermissionDao;
import com.itheima.service.PermissionService;
import com.itheima.system.Permission;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	//查询出权限列表
	@Override
	public List<Permission> findAll() {
		
		return permissionDao.findAll();
	}

	//该方法用于保存新的权限信息
	@Override
	public void save(Permission model) {
		
		permissionDao.save(model);
		
	}
}
