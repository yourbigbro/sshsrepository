package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ZuoYe1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//ͨ��JDBC���� �������µ����ͳ�������3ϵ������28.3W�ļ۸���ӵ����ݿ��car��
		DriverManager.registerDriver(new Driver());//ע�������ĵ�һ�ַ��������Ƽ���
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");//���������ֱ���Ҫ���ӵ����ݿ⣬�û��������롣
		PreparedStatement ps = connection.prepareStatement("insert into car (cname,company,grade,price) values(?,?,?,?)");//��sqlע�룬ע���ұ���prepareStatement�������PreparedStatement��
		ps.setString(1, "������ϵ");
		ps.setString(2, "����");
		ps.setString(3, "���ͳ�");
		ps.setDouble(4, 28.3);
		int eu = ps.executeUpdate();//���صĻ���Ӱ��ı���е���������һ������ֵ�����Բ���close()������executeQuery()��executeUpdate��
		System.out.println("�����������Ϊ"+eu);
		ps.close();
		connection.close();
	}

}
