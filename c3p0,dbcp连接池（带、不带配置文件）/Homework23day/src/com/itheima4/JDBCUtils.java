package com.itheima4;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//��������ļ���dbcp���ӳر����������Ķ࣬��Ϊ�����Զ����������ļ������������ļ��������ǹ̶��ġ�
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//û�о�̬����飬��Ϊ�����������ӳء�
	
	public static DataSource getDataSource() {
			return dataSource;
	}
}
