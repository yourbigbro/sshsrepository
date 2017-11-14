package com.itheima;

import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//下面的对于类和其中的方法的配置相当于实例化工厂
@Component("c3p0")//该工具类不属于三层中的任意一层，所以只能用Component   //相当于<bean id="c3p0" class="com.itheima.utils.C3P0Utils"></bean>
@PropertySource("classpath:jdbc.properties")//用于加载.properties文件的配置
public class C3P0Utils {
	@Value("${jdbc.driver}")
	private String driver;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
	@Bean(name="dataSource")// 相当于<bean id="dataSource" factory-bean="c3p0" factory-method="createDataSource"></bean>
	public DataSource createDataSource() throws PropertyVetoException{
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		
		return dataSource;
	}
	
	//提供一个占位符配置器，开启PropertSource注解(注意不要写成PropertyResource)
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
}
