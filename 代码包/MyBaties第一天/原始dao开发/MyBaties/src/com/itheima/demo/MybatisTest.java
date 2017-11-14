package com.itheima.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.domain.User;

//本项目里面他是没用的。但是也没删
public class MybatisTest {

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
	
	//junit包是jdk里面的jar包
	//根据id查找user对象
	@Test
	public void testQueryUserById(){
		
		//用init初始化方法中得到的连接池创建连接
		SqlSession openSession = sqlSessionFactory.openSession();
		//使用User.xml中配置的方法
		User user = openSession.selectOne("test.queryUserById",1);
		System.out.println(user);
		//释放资源
		openSession.close();
	}
	
	//根据用户名模糊查询user对象(第一种方法)
	@Test
	public void testQueryUserByUsername1(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		//使用User.xml中配置的方法
		List<User> list = openSession.selectList("test.queryUserByUsername1","%小%");
		System.out.println(list);
		openSession.close();
	}
	
	//根据用户名模糊查询user对象(第二种方法)
	@Test
	public void testQueryUserByUsername2(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		//使用User.xml中配置的方法
		List<User> list = openSession.selectList("test.queryUserByUsername2","小");
		System.out.println(list);
		openSession.close();
	}
	
	//插入新对象
	@Test
	public void testSaveUser(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setUsername("小吕");
		user.setBirthday(new Date());
		user.setAddress("东三条");
		user.setSex("男");
		//使用User.xml中配置的方法
		//注意查询时是selectList和selectOne，保存时是insert。但都是从连接上调用的方法
		openSession.insert("saveUser", user);
		System.out.println(user);
		//注意，不提交事务的话就不能成功插入数据，但是还不会报错
		openSession.commit();
		openSession.close();
	}
	
	//修改对象username属性
	//注意，不用修改的属性就不用再写一遍了
	@Test
	public void testUpdateUser(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setId(29);
		user.setUsername("你麻痹");
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
	
	//根据id删除用户对象
	@Test
	public void testDeleteUser(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		//使用User.xml中配置的方法
		openSession.delete("deleteUser", 29);
		openSession.commit();
		openSession.close();
	}
}
