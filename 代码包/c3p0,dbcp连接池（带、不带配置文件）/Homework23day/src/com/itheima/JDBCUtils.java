package com.itheima;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
//�ù�������������������������������ӳأ��������ӳأ�����Ѿ����������õ����ӳء�
public class JDBCUtils {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/day24";//day24��ʾ���ݿ���
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	private static BasicDataSource dataSource = new BasicDataSource();//����jdbc���ӳأ��������ٴ������ӣ�
	static {//�þ�̬������������ӳأ���֤��ִֻ��һ�Σ����ڼ������ʱ�򣩲����ǹ��õġ�����Ҫ���ǣ����Ǿ�̬�����Ļ��Ͳ���ֱ���������������ӳأ������봴���¶���
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
	}
	
	public static DataSource getDataSource() {//ͨ��������ô��������ӳأ����������ӣ�
		return dataSource;
	}
}
