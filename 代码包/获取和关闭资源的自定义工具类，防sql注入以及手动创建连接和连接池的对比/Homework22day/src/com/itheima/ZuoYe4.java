package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;//注意，导入的包是这个而不是带jdbc字样的包。


public class ZuoYe4 {
//通过JDBC，查询价格大于10W的所有汽车信息，并把信息显示在控制台中
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");
		Statement cs = connection.createStatement();//不能防止sql注入。
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
