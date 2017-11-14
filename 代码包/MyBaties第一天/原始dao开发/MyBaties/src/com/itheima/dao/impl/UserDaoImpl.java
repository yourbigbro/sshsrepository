package com.itheima.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public class UserDaoImpl implements UserDao {
	
	SqlSessionFactory sessionFactory=null;
	
	//等待构造函数为sessionFactory变量赋值(在测试类中进行构造函数)
	public UserDaoImpl(SqlSessionFactory sessionFactory) {
		super();
		this.sessionFactory=sessionFactory;
	}

	@Override
	public void testQueryUserById(Integer id) {
		
		//用init初始化方法中得到的连接池创建连接
		SqlSession openSession = sessionFactory.openSession();
		//使用User.xml中配置的方法
		User user = openSession.selectOne("test.queryUserById",id);
		System.out.println(user);
		//释放资源
		openSession.close();
		
	}

	@Override
	public void testQueryUserByUsername1(String username) {
		
		SqlSession openSession = sessionFactory.openSession();
		//使用User.xml中配置的方法
		List<User> list = openSession.selectList("test.queryUserByUsername1","%小%");
		System.out.println(list);
		openSession.close();
		
	}

	@Override
	public void testQueryUserByUsername2(String username) {
		
		SqlSession openSession = sessionFactory.openSession();
		//使用User.xml中配置的方法
		List<User> list = openSession.selectList("test.queryUserByUsername2","小");
		System.out.println(list);
		openSession.close();
		
	}

	@Override
	public void testSaveUser(User user) {
		
		SqlSession openSession = sessionFactory.openSession();
		/*User user = new User();
		user.setUsername("小吕");
		user.setBirthday(new Date());
		user.setAddress("东三条");
		user.setSex("男");*/
		//使用User.xml中配置的方法
		//注意查询时是selectList和selectOne，保存时是insert。但都是从连接上调用的方法
		openSession.insert("saveUser", user);
		System.out.println(user);
		//注意，不提交事务的话就不能成功插入数据，但是还不会报错
		openSession.commit();
		openSession.close();
		
	}

	@Override
	public void testUpdateUser(User user) {
		
		SqlSession openSession = sessionFactory.openSession();
		/*User user = new User();
		user.setId(29);
		user.setUsername("你麻痹");*/
		//user.setBirthday(new Date());
		//user.setAddress("东三条");
		//user.setSex("男");
		//使用User.xml中配置的方法
		//注意查询时是selectList和selectOne，保存时是insert。但都是从连接上调用的方法
		openSession.update("updateUser", user);
		//注意，即使数据库里面其他字段有值，在这里也是null。可能原理和id为null是一样的吧
		System.out.println(user);
		openSession.commit();
		openSession.close();
		
	}

	@Override
	public void testDeleteUser(Integer id) {
		
		SqlSession openSession = sessionFactory.openSession();
		//使用User.xml中配置的方法
		openSession.delete("deleteUser", 29);
		openSession.commit();
		openSession.close();
		
	}

}
