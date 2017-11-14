package com.itheima.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;

public class UserTest {

	private SqlSessionFactory sessionFactory;
	
	@Before
	public void init() throws Exception{
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//加载配置文件
		InputStream stream = Resources.getResourceAsStream("SqlMapConfig.xml");
		//根据配置文件创建连接池
		sessionFactory = builder.build(stream);
	}
	
	//根据id查找user对象
	@Test
	public void testQueryUserById(){
		UserDao userDao = new UserDaoImpl(sessionFactory);
		userDao.testQueryUserById(31);
	}
}
