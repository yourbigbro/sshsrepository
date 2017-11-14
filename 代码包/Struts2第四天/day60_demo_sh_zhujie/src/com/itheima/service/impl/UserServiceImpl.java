package com.itheima.service.impl;

import com.itheima.dao.IuserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
	public User checkUser(User user) {
		IuserDao dao=new UserDaoImpl();
		User user2=dao.checkUser(user);
		return user2;
	}

}
