package com.itheima.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.domain.Orders;
import com.itheima.domain.OrdersUser;
import com.itheima.domain.QueryVo;
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
		//生成UserMapper接口的代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.queryUserById(31);
		System.out.println(user);
		sqlSession.close();
	}
	
	//添加用户
	@Test
	public void saveUser(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		//封装QueryVo类对象
		QueryVo vo = new QueryVo();
		User user = new User();
		user.setUsername("王世豪");
		user.setSex("女");
		user.setBirthday(new Date());
		user.setAddress("天安门");
		vo.setUser(user);
		userMapper.saveUser(vo);
		openSession.commit();
		openSession.close();
	}
	
	
	//修改用户
	@Test
	public void updateUser(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		//封装QueryVo类对象
		QueryVo vo = new QueryVo();
		User user = new User();
		//user中没设置的属性不会被改变，会保持原样
		user.setId(33);
		user.setUsername("王八");
		vo.setUser(user);
		userMapper.updateUser(vo);
		openSession.commit();
		openSession.close();
	}
	
	//根据用户名和姓名查询user集合对象(注意，模糊查询也可以传入对象，因为归根到底取的是对象中的属性值)
	@Test
	public void findUserByUsernameAndSex(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		User user = new User();
		user.setSex("女");
		user.setUsername("世");
		List<User> list = userMapper.findUserByUsernameAndSex(user);
		System.out.println(list);
	}
	
	//根据用户名和姓名查询user集合对象(注意，模糊查询也可以传入对象，因为归根到底取的是对象中的属性值)
	//在UserMapper.xml中使用<where元素>
	@Test
	public void findUserByUsernameAndSex1(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		User user = new User();
		user.setSex("女");
		user.setUsername("世");
		List<User> list = userMapper.findUserByUsernameAndSex1(user);
		System.out.println(list);
	}
	
	//根据用户名和姓名查询user集合对象(注意，模糊查询也可以传入对象，因为归根到底取的是对象中的属性值)
	//在UserMapper.xml中提取sql
	@Test
	public void findUserByUsernameAndSex2(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		User user = new User();
		user.setSex("女");
		user.setUsername("世");
		List<User> list = userMapper.findUserByUsernameAndSex2(user);
		System.out.println(list);
	}
	
	@Test
	public void findUserBySex(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		ArrayList<String> list = new ArrayList<>();
		//list集合用add()方法添加元素
		list.add("1");
		list.add("2");
		List<User> bySex = userMapper.findUserBySex(list);
		System.err.println(bySex);
	}
	
	//用returnType的形式一对一关联查询order和user
	@Test
	public void oneToOneByReturnType(){
		
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		List<OrdersUser> list = userMapper.oneToOneByReturnType();
		System.out.println(list);
	}
	
	//用returnMap的形式一对一关联查询order和user(一看到returnMap立马就想到对象里面嵌套对象)
	@Test
	public void oneToOneByReturnMap(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		List<Orders> list = userMapper.oneToOneByReturnMap();
		//注意，createtime是无法打印出来的，因为他是对象。要想查看的话需要用debug断点
		System.out.println(list);
	}
	
	//用returnMap的形式一对多关联查询order和user
	@Test
	public void oneToManyByReturnMap(){
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		List<User> list = userMapper.oneToManyByReturnMap();
		System.out.println(list);
	}
	
}
