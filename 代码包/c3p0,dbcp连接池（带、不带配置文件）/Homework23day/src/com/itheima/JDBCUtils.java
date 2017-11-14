package com.itheima;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
//该工具类里面进行了三个操作，创建连接池，配置连接池，获得已经创建和配置的连接池。
public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/day24";//day24表示数据库名
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	private static BasicDataSource dataSource = new BasicDataSource();//创建jdbc连接池（而不用再创建连接）
	static {//用静态代码块配置连接池，保证他只执行一次（即在加载类的时候）并且是公用的。最重要的是，不是静态代码块的话就不能直接用类名调用连接池，而必须创建新对象。
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
	}
	
	public static DataSource getDataSource() {//通过方法获得创建的连接池（而不是连接）
		return dataSource;
	}
}
