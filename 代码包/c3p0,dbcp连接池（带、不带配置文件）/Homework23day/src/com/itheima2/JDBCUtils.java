package com.itheima2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JDBCUtils {
	
	private static DataSource dataSource ;
	static{//���Ǿ�̬�����Ļ��Ͳ���ֱ���������������ӳأ������봴���¶���
		InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");//��ȡ�ļ���ת����������
		Properties pro=new Properties();
		try {
			pro.load(is);//��Properties���������������
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dataSource=BasicDataSourceFactory.createDataSource(pro);//�ý����Ľ������dbcp���ӳأ���dataSource��ֵ��
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static DataSource getDataSource() {
		
		return dataSource;
	}
}

