package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//�����࣬���𴴽����Ӻ͹ر������ͷ���Դ
public class Utilss {
	public static Connection getConn() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");//ע�������ĵڶ��ַ������Ƽ���
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");//���Ӻ����ӳص�����������ӳز���Ҫע�����������ӣ���Ϊ�������ļ�������û�������ļ��Ļ���ȻҪ�ֶ����ã�ֻ�����õķ�����ͬ����
		return conn;
		
	}
	/*//����query��
	public static void close(ResultSet eq,Statement cs,Connection connection) throws SQLException {//ע���⼸������Ĳ������������ͼ����Ⱥ�˳��
		eq.close();
		cs.close();
		connection.close();
	}
	//����update��
	public static void close(Statement cs,Connection connection) throws SQLException {//ע���⼸������Ĳ������������ͼ����Ⱥ�˳��
		cs.close();
		connection.close();
	}*/
	//�����д�ü�������̫�ã�Ӧ���ж��ǲ���null�ٽ���close�����ܽ������������һ����
	public static void close(ResultSet eq,Statement cs,Connection connection) throws SQLException {//ע���⼸������Ĳ������������ͼ����Ⱥ�˳��
		if(eq!=null){
			eq.close();
		}
		if(cs!=null){
			cs.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
}
