package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*ͨ��JDBC����ѯ�۸���10W��20W֮�䣬ͬʱ����Ϊ������SUV��
�����ͳ�������������Ϣ��������Ϣ��ʾ�ڿ���̨��*/
public class ZuoYe6 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");
		Statement cs = connection.createStatement();
		ResultSet eq = cs.executeQuery("select * from car where (price between 10 and 20) or (grade in('������SUV','�����ͳ�'))");
		while(eq.next()){
			System.out.println(eq.getString("cname")+"\t\t\t"+eq.getString("company")+"\t\t"+
					eq.getString("grade")+"\t\t"+eq.getDouble("price"));
		}
		Utilss.close(eq,cs,connection);
	}

}
