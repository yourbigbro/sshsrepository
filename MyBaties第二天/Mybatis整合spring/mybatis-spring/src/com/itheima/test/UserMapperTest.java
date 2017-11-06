package com.itheima.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;

public class UserMapperTest {

	private ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		this.context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}

	@Test
	public void testQueryUserById() {
		// 获取Mapper
		UserMapper userMapper = this.context.getBean(UserMapper.class);

		User user = userMapper.queryUserById(1);
		System.out.println(user);
	}

	@Test
	public void testQueryUserByUsername() {
		// 获取Mapper
		UserMapper userMapper = this.context.getBean(UserMapper.class);

		List<User> list = userMapper.queryUserByUsername("张");

		for (User user : list) {
			System.out.println(user);
		}
	}
	@Test
	public void testSaveUser() {
		// 获取Mapper
		UserMapper userMapper = this.context.getBean(UserMapper.class);

		User user = new User();
		user.setUsername("曹操");
		user.setSex("1");
		user.setBirthday(new Date());
		user.setAddress("三国");

		userMapper.saveUser(user);
		System.out.println(user);
	}
}
