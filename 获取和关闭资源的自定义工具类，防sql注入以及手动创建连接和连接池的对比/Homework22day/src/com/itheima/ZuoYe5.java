package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.Utilities;

//ͨ��JDBC����ѯ����Ϊ���ۡ������µϵ�����������Ϣ��������Ϣ��ʾ�ڿ���̨��
public class ZuoYe5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//ʹ�ù�����
		Connection conn = Utilss.getConn();
		PreparedStatement ps = conn.prepareStatement("select * from car where company=? or company=? or company=? ");
		ps.setString(1, "����");
		ps.setString(2, "����");
		ps.setString(3, "�µ�");
		ResultSet eq = ps.executeQuery();
		while(eq.next()){
			System.out.println(eq.getString("cname")+"\t\t\t"+eq.getString("company")+"\t\t"+
					eq.getString("grade")+"\t\t"+eq.getDouble("price"));
		}
		//ʹ�ù�����
		Utilss.close(eq,ps,conn);
	}

}
