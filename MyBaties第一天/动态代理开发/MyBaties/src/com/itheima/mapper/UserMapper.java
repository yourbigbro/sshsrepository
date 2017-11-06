package com.itheima.mapper;

import com.itheima.domain.User;

public interface UserMapper {

	    //根据id查找user对象
		User queryUserById(Integer id);
		
		//根据用户名模糊查询user对象(第一种方法)
		User queryUserByUsername1( String username);
		
		//根据用户名模糊查询user对象(第二种方法)
		User queryUserByUsername2(String username);
		
		//插入新对象
		void saveUser(User user);
		
		//修改对象username属性
		void updateUser(User user);
		
		//根据id删除用户对象
		void deleteUser(Integer id);
}
