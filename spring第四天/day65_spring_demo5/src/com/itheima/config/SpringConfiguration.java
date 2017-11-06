package com.itheima.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.org.glassfish.external.arc.Taxonomy;

//注意，要开启两个扫描
@Configuration
@ComponentScan("com.itheima")//开启注解包扫描
@EnableTransactionManagement //开启事务扫描(扫描各个包)
public class SpringConfiguration {

	@Bean(name="dataSource") //创建连接池
	public DataSource getDataSource() throws PropertyVetoException{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql:///spring_day04");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean  //创建JdbcTemplate
	public JdbcTemplate getjdbcTemplate(@Qualifier("dataSource") DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean //创建平台事务管理器
	public PlatformTransactionManager createTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
