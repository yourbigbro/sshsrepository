package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZuoYe3 {
//ͨ��JDBC����ѯ����Ϊ����"SUV"������������Ϣ��������Ϣ��ʾ�ڿ���̨��
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");
		PreparedStatement ps = connection.prepareStatement("select * from car where grade like ?");
		ps.setString(1, "%SUV%");
		ResultSet eq = ps.executeQuery();
		while(eq.next()){
			System.out.println(eq.getString("cname")+"\t\t\t"+eq.getString("company")+"\t\t"+
		eq.getString("grade")+"\t\t"+eq.getDouble("price"));
		}
		eq.close();
		ps.close();
		connection.close();
		
	}

}
