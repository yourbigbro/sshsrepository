package com.itheima.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.domain.Orders;
import com.itheima.mapper.OrdersMapper;

public class OrdersMapperTest {

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
		
		@Test
		public void findAllOrders(){
			
			SqlSession openSession = sqlSessionFactory.openSession();
			OrdersMapper ordersMapper = openSession.getMapper(OrdersMapper.class);
			List<Orders> orders = ordersMapper.findAllOrders();
			System.out.println(orders);
		}
}

