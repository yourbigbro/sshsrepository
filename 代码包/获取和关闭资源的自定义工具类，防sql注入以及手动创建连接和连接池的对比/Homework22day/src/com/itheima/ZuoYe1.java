package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ZuoYe1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//通过JDBC，将 宝马厂商下的中型车（宝马3系），以28.3W的价格，添加到数据库表car中
		DriverManager.registerDriver(new Driver());//注册驱动的第一种方法（不推荐）
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");//三个参数分别是要连接的数据库，用户名，密码。
		PreparedStatement ps = connection.prepareStatement("insert into car (cname,company,grade,price) values(?,?,?,?)");//防sql注入，注意右边是prepareStatement，左边是PreparedStatement。
		ps.setString(1, "宝马三系");
		ps.setString(2, "宝马");
		ps.setString(3, "中型车");
		ps.setDouble(4, 28.3);
		int eu = ps.executeUpdate();//返回的会受影响的表的行的数量，是一个整型值，所以不用close()。区分executeQuery()和executeUpdate。
		System.out.println("添加汽车个数为"+eu);
		ps.close();
		connection.close();
	}

}
