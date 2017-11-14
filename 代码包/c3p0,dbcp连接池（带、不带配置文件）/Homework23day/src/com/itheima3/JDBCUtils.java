package com.itheima3;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
//与不带配置文件的dbcp连接池的创建方法基本相同
public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/day24";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();//区分ComboPooledDataSource和BasicDataSource。
	static {//不是静态代码块的话就不能直接用类名调用连接池，而必须创建新对象。
		try {
			dataSource.setDriverClass(DRIVER_CLASS_NAME);//与dbcp连接池不同
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(URL);//与dbcp连接池不同
		dataSource.setUser(USERNAME);
		dataSource.setPassword(PASSWORD);
	}
	
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
}
