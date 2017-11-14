package com.itheima4;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//与带配置文件的dbcp连接池比起来简练的多，因为它会自动加载配置文件。但是配置文件的命名是固定的。
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//没有静态代码块，因为不用配置连接池。
	
	public static DataSource getDataSource() {
			return dataSource;
	}
}
