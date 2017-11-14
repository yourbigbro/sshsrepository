package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc 工具类
 * @author lucifer
 *
 */
public class BaseDao {
	
	//静态代码块，只在第一次加载该类的时候执行一次
	
	static {
		try {
			//加载数据库驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			//处理异常
			e.printStackTrace();
		}		
	}
	
	
	
	//BaseDao中的第一个方法
	
	/**
	 * 获取连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConn() throws SQLException{
		String url = "jdbc:oracle:thin:@192.168.10.128:1521:orcl";
		String user = "crm";
		String pwd = "crm";
		return DriverManager.getConnection(url,user,pwd);
	}
	
	
	
	
	//BaseDao中的第二个方法
	
	/**
	 * 关闭资源
	 */
	//该方法的三个参数需要由调用它的方法传入(也就是外面会有全局变量)
	public static void closeAll(ResultSet rs,Statement stat,Connection conn){
		if(rs !=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stat !=null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn !=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
