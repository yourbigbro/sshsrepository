package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;//ע�⣬����İ�����������Ǵ�jdbc�����İ���


public class ZuoYe4 {
//ͨ��JDBC����ѯ�۸����10W������������Ϣ��������Ϣ��ʾ�ڿ���̨��
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");
		Statement cs = connection.createStatement();//���ܷ�ֹsqlע�롣
		ResultSet eq = cs.executeQuery("select * from car where price>10");
		while(eq.next()){
			System.out.println(eq.getString("cname")+"\t\t\t"+eq.getString("company")+"\t\t"+
		eq.getString("grade")+"\t\t"+eq.getDouble("price"));
		}
		eq.close();
		cs.close();
		connection.close();
	}

}
