package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao {

	//注意，初始化方法init()不在这里面，它在测试类里面。UserDaoImpl只声明了一个连接池，初始化连接池是在测试类里面
	//注意，接口中的方法没有{}方法体
	
	//根据id查找user对象
	void testQueryUserById(Integer id);
	
	//根据用户名模糊查询user对象(第一种方法)
	void testQueryUserByUsername1( String username);
	
	//根据用户名模糊查询user对象(第二种方法)
	void testQueryUserByUsername2(String username);
	
	//插入新对象
	void testSaveUser(User user);
	
	//修改对象username属性
	void testUpdateUser(User user);
	
	//根据id删除用户对象
	void testDeleteUser(Integer id);
	
}
