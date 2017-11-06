package com.itheima2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JDBCUtils {
	
	private static DataSource dataSource ;
	static{//不是静态代码块的话就不能直接用类名调用连接池，而必须创建新对象。
		InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");//读取文件并转换成输入流
		Properties pro=new Properties();
		try {
			pro.load(is);//用Properties工具类解析输入流
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dataSource=BasicDataSourceFactory.createDataSource(pro);//用解析的结果创建dbcp连接池，给dataSource赋值。
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static DataSource getDataSource() {
		
		return dataSource;
	}
}

