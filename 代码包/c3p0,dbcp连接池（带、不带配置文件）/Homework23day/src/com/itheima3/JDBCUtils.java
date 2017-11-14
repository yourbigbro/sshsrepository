package com.itheima3;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
//�벻�������ļ���dbcp���ӳصĴ�������������ͬ
public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/day24";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();//����ComboPooledDataSource��BasicDataSource��
	static {//���Ǿ�̬�����Ļ��Ͳ���ֱ���������������ӳأ������봴���¶���
		try {
			dataSource.setDriverClass(DRIVER_CLASS_NAME);//��dbcp���ӳز�ͬ
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(URL);//��dbcp���ӳز�ͬ
		dataSource.setUser(USERNAME);
		dataSource.setPassword(PASSWORD);
	}
	
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
}
