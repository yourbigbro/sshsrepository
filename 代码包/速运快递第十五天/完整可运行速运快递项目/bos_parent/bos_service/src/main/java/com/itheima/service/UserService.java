package com.itheima.service;

import java.util.List;

import com.itheima.system.User;

public interface UserService {

	List<User> findAll();

	void save(User model, String[] roleIds);

}
