package com.itheima;

import java.lang.ThreadLocal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ThreadLocalUtils {
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();//ThreadLocal工具类，是静态变量，目的是存储连接。也为了能让静态方法引用。
	private static DataSource ds=new ComboPooledDataSource();//c3p0连接池，也是静态的。
	//每一个方法里面都要调用getConn是因为开启，提交，回滚事务的对象都是连接。并且他们当中的连接都是保存在tl工具类中的同一个连接。
	//由连接池建立连接
	//服务层和持久层都用到了ThreadLocalUtils工具类（中的方法中的ThreadLocal工具类储存的连接池中的连接），前者是因为连接，开启事务，提交和回滚事务，后者是因为查询语句要用到连接。
	public static Connection getConn(){
		Connection conn = tl.get();
		if(conn==null){
			try {
				conn = ds.getConnection();//从连接池当中获得一个连接存储在工具类当中。
				tl.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("创建连接失败");
			}
		}
		return conn;
	}
	//开启事务
	public static void start(){
		System.out.println("奶奶");
		Connection conn = getConn();//调用上面刚刚创建的方法
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("开启事务失败");
		}
	}
	//提交事务
	public static void commit(){
		Connection conn = getConn();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("提交事务失败");
		}
	}
	//回滚事务
	public static void rollback(){
		Connection conn = getConn();
		try {
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("回滚失败");
		}
			
		
	}
}
