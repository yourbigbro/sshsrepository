package com.itheima.mapper;

import java.util.List;

import com.itheima.domain.Orders;
import com.itheima.domain.OrdersUser;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

public interface UserMapper {

	    //根据id查找user对象
		User queryUserById(Integer id);
		
		//根据用户名模糊查询user对象(第一种方法)
		User queryUserByUsername1( String username);
		
		//根据用户名模糊查询user对象(第二种方法)
		User queryUserByUsername2(String username);
		
		//插入新对象
		void saveUser(QueryVo vo);
		
		//修改对象username属性
		void updateUser(QueryVo vo);
		
		//根据id删除用户对象
		void deleteUser(Integer id);
		
		List<User> findUserByUsernameAndSex(User user);
		
		List<User> findUserByUsernameAndSex1(User user);
		
		List<User> findUserByUsernameAndSex2(User user);
		
		List<User> findUserBySex(List<String> list);
		
		List<OrdersUser> oneToOneByReturnType();
		
		List<Orders> oneToOneByReturnMap();
		
		List<User> oneToManyByReturnMap();
}
