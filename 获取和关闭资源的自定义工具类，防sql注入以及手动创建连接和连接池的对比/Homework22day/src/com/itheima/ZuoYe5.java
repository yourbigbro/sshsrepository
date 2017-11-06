package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.Utilities;

//通过JDBC，查询厂商为奔驰、宝马、奥迪的所有汽车信息，并把信息显示在控制台中
public class ZuoYe5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//使用工具类
		Connection conn = Utilss.getConn();
		PreparedStatement ps = conn.prepareStatement("select * from car where company=? or company=? or company=? ");
		ps.setString(1, "奔驰");
		ps.setString(2, "宝马");
		ps.setString(3, "奥迪");
		ResultSet eq = ps.executeQuery();
		while(eq.next()){
			System.out.println(eq.getString("cname")+"\t\t\t"+eq.getString("company")+"\t\t"+
					eq.getString("grade")+"\t\t"+eq.getDouble("price"));
		}
		//使用工具类
		Utilss.close(eq,ps,conn);
	}

}
