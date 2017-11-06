package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//工具类，负责创建连接和关闭连接释放资源
public class Utilss {
	public static Connection getConn() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");//注册驱动的第二种方法（推荐）
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day23","root","root");//连接和连接池的区别就是连接池不需要注册驱动和连接（因为有配置文件。假如没有配置文件的话依然要手动设置，只是设置的方法不同。）
		return conn;
		
	}
	/*//用于query。
	public static void close(ResultSet eq,Statement cs,Connection connection) throws SQLException {//注意这几个传入的参数的数据类型及其先后顺序。
		eq.close();
		cs.close();
		connection.close();
	}
	//用于update。
	public static void close(Statement cs,Connection connection) throws SQLException {//注意这几个传入的参数的数据类型及其先后顺序。
		cs.close();
		connection.close();
	}*/
	//上面的写好几个，不太好，应该判断是不是null再进行close这样能将两个函数变成一个。
	public static void close(ResultSet eq,Statement cs,Connection connection) throws SQLException {//注意这几个传入的参数的数据类型及其先后顺序。
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
