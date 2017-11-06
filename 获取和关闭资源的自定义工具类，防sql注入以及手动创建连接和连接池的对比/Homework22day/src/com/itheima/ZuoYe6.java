package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*通过JDBC，查询价格在10W到20W之间，同时级别为紧凑型SUV、
紧凑型车的所有汽车信息，并把信息显示在控制台中*/
public class ZuoYe6 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");
		Statement cs = connection.createStatement();
		ResultSet eq = cs.executeQuery("select * from car where (price between 10 and 20) or (grade in('紧凑型SUV','紧凑型车'))");
		while(eq.next()){
			System.out.println(eq.getString("cname")+"\t\t\t"+eq.getString("company")+"\t\t"+
					eq.getString("grade")+"\t\t"+eq.getDouble("price"));
		}
		Utilss.close(eq,cs,connection);
	}

}
