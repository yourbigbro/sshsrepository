package com.itheima;

import java.lang.ThreadLocal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ThreadLocalUtils {
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();//ThreadLocal�����࣬�Ǿ�̬������Ŀ���Ǵ洢���ӡ�ҲΪ�����þ�̬�������á�
	private static DataSource ds=new ComboPooledDataSource();//c3p0���ӳأ�Ҳ�Ǿ�̬�ġ�
	//ÿһ���������涼Ҫ����getConn����Ϊ�������ύ���ع�����Ķ��������ӡ��������ǵ��е����Ӷ��Ǳ�����tl�������е�ͬһ�����ӡ�
	//�����ӳؽ�������
	//�����ͳ־ò㶼�õ���ThreadLocalUtils�����ࣨ�еķ����е�ThreadLocal�����ഢ������ӳ��е����ӣ���ǰ������Ϊ���ӣ����������ύ�ͻع����񣬺�������Ϊ��ѯ���Ҫ�õ����ӡ�
	public static Connection getConn(){
		Connection conn = tl.get();
		if(conn==null){
			try {
				conn = ds.getConnection();//�����ӳص��л��һ�����Ӵ洢�ڹ����൱�С�
				tl.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("��������ʧ��");
			}
		}
		return conn;
	}
	//��������
	public static void start(){
		System.out.println("����");
		Connection conn = getConn();//��������ոմ����ķ���
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("��������ʧ��");
		}
	}
	//�ύ����
	public static void commit(){
		Connection conn = getConn();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�ύ����ʧ��");
		}
	}
	//�ع�����
	public static void rollback(){
		Connection conn = getConn();
		try {
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�ع�ʧ��");
		}
			
		
	}
}
