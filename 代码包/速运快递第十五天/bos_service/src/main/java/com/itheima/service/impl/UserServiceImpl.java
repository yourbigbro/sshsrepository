package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import com.itheima.system.Role;
import com.itheima.system.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	//该方法用于查询用户列表
	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}

	//该方法用于保存新用户
	@Override
	public void save(User model,String[] roleIds) {
		
		//只有先持久化之后才能使用hibernate一级缓存
		userDao.save(model);
		
		//遍历roleIds数组并查询出对应的role对象并加入user中
		for (String roleId : roleIds) {
			
			Role role = roleDao.findOne(Integer.parseInt(roleId));
			model.getRoles().add(role);
		}
		
	}
}
