package com.itheima.test;

import java.beans.PropertyVetoException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.itheima.domain.Account;
import com.itheima.rowmapper.MyRowMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

//该类用于测试jdbc模板
public class TestJDBCTemplate {

	//用配置文件的方式创建连接池见applicationContext.xml配置文件
	@Test
	public void test() throws PropertyVetoException{
		//java代码创建连接池方式一(c3p0连接池的方式)
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql///spring_day04");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		
		//java代码创建连接池方式二(dbcp连接池的方式)
		BasicDataSource dataSource2 = new BasicDataSource();//注意观察导哪个包
		dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource2.setUrl("jdbc:mysql///spring_day04");
		dataSource2.setUsername("root");
		dataSource2.setPassword("root");
		
		//java代码创建连接池方式三(Spring框架自己的方式)
		DriverManagerDataSource dataSource3 = new DriverManagerDataSource();
		dataSource3.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource3.setUrl("jdbc:mysql///spring_day04");
		dataSource3.setUsername("root");
		dataSource3.setPassword("root");
		
		//配置jdbcTemplate(JDBC模板)的xml配置文件方法(特点是不用传入DataSource连接池对象，因为配置文件中已经配置了)
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//提示中的add cast表示强转
		//随机选择了c3p0模板
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate1");
		
		//注意，增删改都用update()
		//配置jdbcTemplate的java代码方法
		JdbcTemplate jdbcTemplate2 = new JdbcTemplate();
		//此处随机选择了dataSource3
		jdbcTemplate2.setDataSource(dataSource3);
		jdbcTemplate2.update("INSERT INTO account VALUES(NULL,?,?)","菜10",10000);
		
		//查询数据用query()
		//当查询数据并返回Account对象或其集合时，第二个参数就是new MyRowMapper()
		List<Account> query = jdbcTemplate2.query("SELECT * FROM account", new MyRowMapper());
		
		//当有参数要传入sql语句时，要将参数依次放在最后
		List<Account> list = jdbcTemplate2.query("SELECT * FROM account WHERE id=?", new MyRowMapper(),5);
		
		//当查询的结果是某一基本类型时，规则如下所示
		Integer count = jdbcTemplate2.queryForObject("SELECT COUNT(*) FROM account where id>?", Integer.class,2);
	}
}
