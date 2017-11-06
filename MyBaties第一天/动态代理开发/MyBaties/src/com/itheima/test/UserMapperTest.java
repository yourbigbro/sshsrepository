package com.itheima.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;

public class UserMapperTest {

	//代理mapper测试类
	
	//创建连接工厂对象
	private SqlSessionFactory sqlSessionFactory=null;
	
	//当jdk中没有junit时，应该先根据@Test来导包，再使用@Before就行了
	@Before
	public void init() throws Exception{
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//加载配置文件
		InputStream stream = Resources.getResourceAsStream("SqlMapConfig.xml");
		//根据配置文件创建连接池
		sqlSessionFactory = builder.build(stream);
	}
	
	//根据id查询user对象
	@Test
	public void testQueryUserById(){
	    
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.queryUserById(31);
		System.out.println(user);
		sqlSession.close();
	}
}
